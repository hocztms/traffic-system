package trafficsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import trafficsystem.config.IdAutoConfiguration;
import trafficsystem.config.IdProperties;
import trafficsystem.entity.AdminEntity;
import trafficsystem.entity.IllegalEntity;
import trafficsystem.enums.IllegalEnum;
import trafficsystem.service.AdminService;
import trafficsystem.service.IllegalService;

import java.util.Date;

@SpringBootTest
class TrafficSystemApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IllegalService illegalService;
    @Autowired
    private IdProperties idProperties;

    @Test
    void contextLoads() {
//        adminService.insertAdmin(new AdminEntity("35011120010728157X",passwordEncoder.encode("123456"),1));
//        illegalService.insertIllegal(new IllegalEntity(0, IllegalEnum.PEDESTRIAN,"福大生活三区","sds","sds",new Date()));
//        System.out.printf(idProperties.toString());
//        System.out.printf(String.valueOf(IdAutoConfiguration.check("黄欧成","35011120010728187X")));
    }

}
