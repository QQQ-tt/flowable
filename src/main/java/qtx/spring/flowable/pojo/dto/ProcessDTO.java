package qtx.spring.flowable.pojo.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Data
public class ProcessDTO {

    private String processInstanceId;

    private String taskId;

    private String user;

    private Map<String, Object> variables;
}
