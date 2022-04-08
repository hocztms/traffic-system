package trafficsystem.security.servie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trafficsystem.entity.AdminEntity;
import trafficsystem.security.entity.MyUserDetails;
import trafficsystem.service.AdminService;

@Service
@Slf4j
public class MyAdminDetailServiceImpl implements UserDetailsService {


    @Autowired
    private AdminService adminService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminEntity adminEntity = adminService.findAdminByIDCard(username);
        if(adminEntity ==null){
            throw new RuntimeException(username+"账号不存在");
        }
        log.info(adminEntity.toString());
//        List<AdminRoleEntity> adminRoleEntities = adminService.findAdminRolesByUsername(username);
//        List<GrantedAuthority> authoritys = new ArrayList<>();
//        for (AdminRoleEntity adminRoleEntity : adminRoleEntities){
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(adminRoleEntity.getRole());
//            authoritys.add(simpleGrantedAuthority);
//        }

        return new MyUserDetails(adminEntity.getIdCard(), adminEntity.getPassword(),null);
    }
}
