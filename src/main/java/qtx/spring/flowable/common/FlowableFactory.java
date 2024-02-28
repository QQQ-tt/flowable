package qtx.spring.flowable.common;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.TaskVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Component
public class FlowableFactory {

    @Autowired
    private ProcessEngine processEngine;


    public RepositoryService getRepositoryService() {
        return processEngine.getRepositoryService();
    }

    public RuntimeService getRuntimeService() {
        return processEngine.getRuntimeService();
    }

    public TaskService getTaskService(){
        return processEngine.getTaskService();
    }

    public IdentityService getIdentityService() {
        return processEngine.getIdentityService();
    }

    public HistoryService getHistoryService() {
        return processEngine.getHistoryService();
    }

    public DynamicBpmnService getDynamicBpmnService() {
        return processEngine.getDynamicBpmnService();
    }

    @NotNull
    protected List<TaskVO> getTaskVOList(List<Task> taskList) {
        List<TaskVO> list = new ArrayList<>();
        taskList.forEach(task -> list.add(TaskVO.builder()
                .id(task.getId())
                .name(task.getName())
                .build()));
        return list;
    }

    @NotNull
    protected List<DeploymentVO> getDeploymentVOList(List<Deployment> list) {
        List<DeploymentVO> vos = new ArrayList<>();
        list.forEach(deployment -> vos.add(DeploymentVO.builder()
                .id(deployment.getId())
                .name(deployment.getName())
                .build()));
        return vos;
    }
}
