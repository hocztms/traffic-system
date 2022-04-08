package trafficsystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trafficsystem.entity.IllegalEntity;
import trafficsystem.mapper.IllegalMapper;
import trafficsystem.service.IllegalService;

@Service
public class IllegalServiceImpl implements IllegalService {
    @Autowired
    private IllegalMapper illegalMapper;
    @Override
    public Integer insertIllegal(IllegalEntity illegalEntity) {
        return illegalMapper.insert(illegalEntity);
    }

    @Override
    public void updateIllegal(IllegalEntity illegalEntity) {
        illegalMapper.updateById(illegalEntity);
    }

    @Override
    public IllegalEntity findIllegalById(Integer id) {
        return illegalMapper.selectById(id);
    }

    @Override
    public Page<IllegalEntity> findIllegalsByEquipment(String equipmentName, Page<IllegalEntity> entityPage) {
        QueryWrapper<IllegalEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("equipment_name",equipmentName);
        return illegalMapper.selectPage(entityPage,wrapper);
    }
}
