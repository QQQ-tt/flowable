package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang
 * @since 2024/2/29 17:57
 */
@Data
@Builder
public class CommentVO {

    private String taskId;

    private String comment;

    private String type;

    private String userId;
}
