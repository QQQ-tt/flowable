package qtx.spring.flowable.service.impl;

import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
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
    public List<DeploymentVO> processAll() {
        List<Deployment> list = getRepositoryService().createDeploymentQuery()
                .orderByDeploymentId()
                .asc()
                .list();
        return getDeploymentVOList(list);
    }
}
