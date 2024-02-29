package qtx.spring.flowable.pojo.dto;

import lombok.Data;

/**
 * @author huang
 * @since 2024/2/29 17:06
 */
@Data
public class VariablesParamDTO {

    String processInstanceId;

    String taskId;

    String processDefinitionId;
}
