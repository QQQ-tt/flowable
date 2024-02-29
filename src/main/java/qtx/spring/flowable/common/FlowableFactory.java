package qtx.spring.flowable.common;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.ProcessDefinitionVO;
import qtx.spring.flowable.pojo.vo.TaskVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Slf4j
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

    public TaskService getTaskService() {
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


        list.forEach(deployment -> {
            String id = deployment.getId();
            List<ProcessDefinition> processDefinitions = getRepositoryService().createProcessDefinitionQuery()
                    .deploymentId(id)
                    .list();
            List<ProcessDefinitionVO> arrayList = new ArrayList<>();
            processDefinitions.forEach(processDefinition -> {
                ProcessDefinitionVO processDefinitionVO = ProcessDefinitionVO.builder()
                        .id(processDefinition.getId())
                        .name(processDefinition.getName())
                        .build();
                log.info("processDefinition:{}", processDefinition);
                arrayList.add(processDefinitionVO);
            });
            vos.add(DeploymentVO.builder()
                    .id(id)
                    .name(deployment.getName())
                    .processDefinitionList(arrayList)
                    .build());
        });
        return vos;
    }
}
