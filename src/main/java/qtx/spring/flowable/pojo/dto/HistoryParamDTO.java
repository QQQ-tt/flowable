package qtx.spring.flowable.pojo.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author huang
 * @since 2024/2/29 16:05
 */
@Data
@Builder
public class HistoryParamDTO {

    private String processDefinitionId;

    private String processInstanceId;
}
