package qtx.spring.flowable.pojo.dto;

import lombok.Data;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Data
public class TaskParamDTO {

    private String processInstanceId;

    private String assignee;

}
