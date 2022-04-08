package trafficsystem.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import trafficsystem.enums.IllegalEnum;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IllegalVo {
    private Integer type;
    private Date statTime;
    private Date endTime;
    private String licensePlate;
}
