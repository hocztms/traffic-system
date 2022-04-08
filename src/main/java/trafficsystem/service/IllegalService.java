package trafficsystem.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import trafficsystem.entity.IllegalEntity;

public interface IllegalService {
    Integer insertIllegal(IllegalEntity illegalEntity);

    void updateIllegal(IllegalEntity illegalEntity);

    IllegalEntity findIllegalById(Integer id);

    Page<IllegalEntity> findIllegalsByEquipment(String equipmentName,Page<IllegalEntity> entityPage);
}
