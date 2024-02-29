package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author qtx
 * @since 2024/2/29
 */
@Data
@Builder
public class ProcessDefinitionVO {

    private String id;

    private String name;
}
