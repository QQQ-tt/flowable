package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author qtx
 * @since 2024/2/29
 */
@Data
@Builder
public class UserVO {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
