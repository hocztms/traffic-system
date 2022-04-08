package trafficsystem.service;


import trafficsystem.commons.RestResult;
import trafficsystem.vo.AdminRegisterVo;
import trafficsystem.vo.AdminVo;

public interface AuthService {
    RestResult adminLogin(AdminVo adminLoginVo);

    RestResult adminRegister(AdminRegisterVo adminRegisterVo);
}
