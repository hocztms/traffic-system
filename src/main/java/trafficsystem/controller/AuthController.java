package trafficsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trafficsystem.commons.RestResult;
import trafficsystem.enums.StatusEnum;
import trafficsystem.redis.RedisService;
import trafficsystem.service.AuthService;
import trafficsystem.utils.ResultUtils;
import trafficsystem.vo.AdminRegisterVo;
import trafficsystem.vo.AdminVo;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RedisService redisService;


    @PostMapping("/login")
    public RestResult userLogin(@RequestBody AdminVo adminVo){
        if (!redisService.checkUserLoginLimit(adminVo.getIdCard())) {
            return ResultUtils.error("登入失败过多稍后再试");
        }

        return authService.adminLogin(adminVo);
    }
    @PostMapping("/register")
    public RestResult userRegister(@RequestBody AdminRegisterVo adminVo){
        return authService.adminRegister(adminVo);
    }

}
