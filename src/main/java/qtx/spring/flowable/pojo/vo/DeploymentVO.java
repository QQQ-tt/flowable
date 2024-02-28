package qtx.spring.flowable.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Data
@Builder
public class DeploymentVO {

    private String id;

    private String name;
}