package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author qtx
 * @since 2024/2/28
 */
@Data
@Builder
public class TaskVO {

    private String id;

    private String name;

    private Date createTime;

    private int priority;
}
