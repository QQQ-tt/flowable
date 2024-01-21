package qtx.spring.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = FlowableApplication.class, properties = {"vm_ip=192.168.77.130"})
class FlowableApplicationTests {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    void contextLoads() {
        System.out.println("~~~~~~~~~");
    }

    @Test
    void testRunProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", "张三");// 谁申请请假
        variables.put("day", 3); // 请几天假
        variables.put("description", "工作累了，想出去玩玩"); // 请假的原因
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("leave", variables);
        // 输出相关的流程实例信息
        System.out.println("流程定义的ID：" + leave.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + leave.getId());
        System.out.println("当前活动的ID：" + leave.getActivityId());
    }

}
