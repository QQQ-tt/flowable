package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author huang
 * @since 2024/2/29 16:20
 */
@Data
@Builder
public class HistoryVO {

   private String getActivityId;

    private String activityName;

    private String processInstanceId;

    private String assignee;

    private Long durationInMillis;

}
