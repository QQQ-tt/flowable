package qtx.spring.flowable.util;

import java.util.UUID;

/**
 * @author qtx
 * @since 2024/2/27
 */
public class NumUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
