package trafficsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IllegalsVo {
    private String equipmentName;
    private Date date;
    List<IllegalVo> illegals;
}
