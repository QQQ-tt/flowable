package qtx.spring.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FlowableApplication.class, properties = "vm_ip=192.168.77.130")
class FlowableApplicationTests {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    void contextLoads() {
        System.out.println("~~~~~~~~~");
    }

    /**
     * 手动部署流程
     */
    @Test
    void testDeploy() {
        // 部署流程 获取RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 创建Deployment对象
        Deployment deployment = repositoryService.createDeployment()
                // 添加流程部署文件
                .addClasspathResource("processes1/公司员工请假流程.bpmn20.xml")
                // 设置部署流程的名称
                .name("公司员工请假流程测试")
                // 执行部署操作
                .deploy();
        System.out.println("deployment.getId() = " + deployment.getId());
        System.out.println("deployment.getName() = " + deployment.getName());
    }

    @Test
    void testDeploy2() {
        // 部署流程 获取RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                // 添加流程部署文件
                .addInputStream("请假流程.bpmn20.xml", this.getClass().getClassLoader().getResourceAsStream(
                        "processes1/公司员工请假流程.bpmn20.xml"))
                // 设置部署流程的名称
                .name("公司员工请假流程测试")
                // 执行部署操作
                .deploy();
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery()
                .orderByDeploymentId();
        List<Deployment> deployments = deploymentQuery.list();
    }

    /**
     * 运行流程
     */
    @Test
    void testRunProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 构建流程变量
        Map<String, Object> variables = new HashMap<>();
        // 谁申请请假
        variables.put("studentUser", "张三");
        // 请几天假
        variables.put("day", 3);
        // 请假的原因
        variables.put("description", "工作累了，想出去玩玩");
        ProcessInstance leave = runtimeService.startProcessInstanceByKey("leave", variables);
        // 输出相关的流程实例信息
        System.out.println("流程定义的ID：" + leave.getProcessDefinitionId());
        System.out.println("流程实例的ID：" + leave.getId());
        System.out.println("当前活动的ID：" + leave.getActivityId());
    }

    /**
     * 测试任务用户
     */
    @Test
    void testTaskUser() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("leave")
                .taskAssignee("张三")
                .list();
        System.out.println("任务数量：" + taskList.size());
        taskList.forEach(task -> {
            System.out.println("任务名称：" + task.getName());
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务的创建时间：" + task.getCreateTime());
            System.out.println("任务的优先级：" + task.getPriority());
        });
    }

    /**
     * 查询任务组
     */
    @Test
    void testTaskGroup() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey("leave")
                .list();
        System.out.println("任务数量：" + taskList.size());
        taskList.forEach(task -> {
            System.out.println("任务名称：" + task.getName());
            System.out.println("任务ID：" + task.getId());
            System.out.println("任务的创建时间：" + task.getCreateTime());
        });
    }

    @Test
    void testCompleteTask() {
        TaskService taskService = processEngine.getTaskService();
        // 添加流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("outcome", "通过"); // 拒绝请假
        // 完成任务
        taskService.complete("ba0a3ffc-be57-11ee-8071-60e9aa3ca244", variables);
    }

}
