package trafficsystem.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trafficsystem.enums.IllegalEnum;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_illegal")
public class IllegalEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private IllegalEnum illegalType;
    private String equipmentName;
    private String licensePlate;
    private String path;
    private Date date;
}
