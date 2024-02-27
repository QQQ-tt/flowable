package qtx.spring.flowable.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Getter
public enum DataEnums {

    /**
     * 成功
     */
    SUCCESS("成功", 200),
    /**
     * 失败
     */
    FAILED("失败", 500);

    /**
     * 提示
     */
    private final String msg;
    /**
     * 错误编码
     */
    private final int code;

    DataEnums(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.msg;
    }

    private static final Map<String, Integer> DATE_ENUMS = new HashMap<>();

    static {
        Stream.of(DataEnums.values()).forEach(v -> DATE_ENUMS.put(v.msg, v.code));
    }
}
