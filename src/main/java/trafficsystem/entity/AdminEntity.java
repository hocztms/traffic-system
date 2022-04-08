package trafficsystem.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import trafficsystem.enums.StatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_admin")
@Builder
public class AdminEntity {

    private String idCard;
    private String name;
    private String password;
    private StatusEnum status;
}
