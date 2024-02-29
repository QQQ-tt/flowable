package qtx.spring.flowable.pojo.dto;

import lombok.Data;

/**
 * @author huang
 * @since 2024/2/29 17:16
 */
@Data
public class FinishedTaskQueryDTO {
    /**
     * 流程名称
     */
    private String name;

    /**
     * 人员
     */
    private String assignee;
}
