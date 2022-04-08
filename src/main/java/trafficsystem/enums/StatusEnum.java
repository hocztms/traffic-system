package trafficsystem.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

@Getter
public enum StatusEnum implements IEnum<Integer> {
    VALID(1, "有效"),
    INVALID(0, "无效");

    StatusEnum(Integer value, String status) {
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
