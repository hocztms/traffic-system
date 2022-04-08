package trafficsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trafficsystem.commons.RestResult;
import trafficsystem.config.IdAutoConfiguration;
import trafficsystem.entity.AdminEntity;
import trafficsystem.enums.StatusEnum;
import trafficsystem.redis.RedisService;
import trafficsystem.security.entity.MyUserDetails;
import trafficsystem.service.AdminService;
import trafficsystem.service.AuthService;
import trafficsystem.utils.JwtTokenUtils;
import trafficsystem.utils.ResultUtils;
import trafficsystem.vo.AdminRegisterVo;
import trafficsystem.vo.AdminVo;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public RestResult adminLogin(AdminVo adminLoginVo) {
        Authentication authentication;
        try {
            // 进行身份验证,
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminLoginVo.getIdCard(), adminLoginVo.getPassword()));
        } catch (Exception e) {

            //设置登入密码错误限制
            redisService.setUserLoginLimit(adminLoginVo.getIdCard());
            return new RestResult(0, e.getMessage(), null);
        }

        MyUserDetails loginUser = (MyUserDetails) authentication.getPrincipal();
        RestResult result = new RestResult(1, "登入成功", null);
        result.put("token", jwtTokenUtils.generateToken(loginUser, "admin"));
        return result;
    }

    @Override
    public RestResult adminRegister(AdminRegisterVo adminRegisterVo) {
        try {
            AdminEntity adminByIDCard = adminService.findAdminByIDCard(adminRegisterVo.getIdCard());
            if (adminByIDCard!=null){
                return ResultUtils.error("该身份证已注册");
            }
            if (!IdAutoConfiguration.check(adminByIDCard.getName(),adminByIDCard.getIdCard())){
                return ResultUtils.error("身份信息验证失败,检查姓名与身份证是否匹配");
            }

            adminService.insertAdmin(new AdminEntity(adminRegisterVo.getIdCard(),adminRegisterVo.getName(),passwordEncoder.encode(adminRegisterVo.getPassword()), StatusEnum.VALID));
            return ResultUtils.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.error("用户名已存在");
        }
    }


}
