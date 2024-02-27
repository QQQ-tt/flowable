package qtx.spring.flowable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.service.FlowableQueryService;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@SpringBootTest(classes = FlowableApplication.class, properties = "vm_ip=192.168.77.130")
public class ServiceQueryTest {

    @Autowired
    private FlowableQueryService flowableQueryService;

    @Test
    void testProcessAll() {
        List<DeploymentVO> processAll = flowableQueryService.processAll();
        System.out.println(processAll);
    }
}
