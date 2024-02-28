package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author qtx
 * @since 2024/2/28
 */
@Data
@Builder
public class ProcessInstanceVO {

    private String id;

    private String processDefinitionId;

    private String processDefinitionName;

    private String activityId;
}
