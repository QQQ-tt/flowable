package qtx.spring.flowable.service.impl;

import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.stereotype.Service;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.TaskVO;
import qtx.spring.flowable.service.FlowableQueryService;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Service
public class FlowableQueryServiceImpl extends FlowableFactory implements FlowableQueryService {

    @Override
    public List<TaskVO> taskByUserAndId(TaskParamDTO dto) {
        List<Task> taskList = getTaskService().createTaskQuery()
                .processDefinitionId(dto.getProcessInstanceId())
                .taskAssignee(dto.getAssignee())
                .list();
        return getTaskVOList(taskList);
    }

    @Override
    public List<TaskVO> taskById(TaskParamDTO dto) {
        List<Task> taskList = getTaskService().createTaskQuery()
                .processDefinitionId(dto.getProcessInstanceId())
                .list();
        return getTaskVOList(taskList);
    }

    @Override
    public List<TaskVO> taskList(TaskParamDTO dto) {
        TaskQuery taskQuery = getTaskService().createTaskQuery();
        if (dto.getTaskId() != null) {
            taskQuery.taskId(dto.getTaskId());
        }
        if (dto.getAssignee() != null) {
            taskQuery.taskAssignee(dto.getAssignee());
        }
        if (dto.getProcessInstanceId() != null) {
            taskQuery.processInstanceId(dto.getProcessInstanceId());
        }
        if (dto.getProcessDefinitionId() != null) {
            taskQuery.processDefinitionId(dto.getProcessDefinitionId());
        }
        if (dto.getProcessDefinitionKey() != null) {
            taskQuery.processDefinitionKey(dto.getProcessDefinitionKey());
        }
        if (dto.getTaskName() != null) {
            taskQuery.taskName(dto.getTaskName());
        }
        if (dto.getTaskDefinitionKey() != null) {
            taskQuery.taskDefinitionKey(dto.getTaskDefinitionKey());
        }
        // ...
        return getTaskVOList(taskQuery.list());
    }

    @Override
    public List<DeploymentVO> processAll() {
        List<Deployment> list = getRepositoryService().createDeploymentQuery()
                .orderByDeploymentId()
                .asc()
                .list();
        return getDeploymentVOList(list);
    }
}
