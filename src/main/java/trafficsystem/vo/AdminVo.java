package trafficsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo {

    @NotBlank(message = "身份证号不能为空")
    @Size(max = 20)
    private String idCard;

    @NotBlank(message = "密码不能为空")
    @Size(max = 20)
    private String password;

}
