package trafficsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trafficsystem.entity.AdminEntity;
import trafficsystem.mapper.AdminMapper;
import trafficsystem.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public AdminEntity findAdminByIDCard(String idCard) {
        QueryWrapper<AdminEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("id_card",idCard);
        return adminMapper.selectOne(wrapper);
    }

    @Override
    public void insertAdmin(AdminEntity adminEntity) {
        adminMapper.insert(adminEntity);
    }
}
