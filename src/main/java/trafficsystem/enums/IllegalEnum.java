package trafficsystem.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum IllegalEnum implements IEnum<Integer> {
    SOLID_LINE(0, "压实线"),
    PEDESTRIAN(1, "不礼让行人");

    IllegalEnum(Integer value, String status) {
        this.value = value;
        this.status = status;
    }
    //标记数据库存的值是value
    @EnumValue
    private final Integer value;
    @JsonValue
    private final String status;

    @Override
    public Integer getValue() {
        return value;
    }

}
