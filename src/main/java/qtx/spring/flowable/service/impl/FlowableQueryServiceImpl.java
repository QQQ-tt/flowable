package qtx.spring.flowable.service.impl;

import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.service.FlowableQueryService;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Service
public class FlowableQueryServiceImpl extends FlowableFactory implements FlowableQueryService {

    @Override
    public List<Task> taskByUserAndId(TaskParamDTO dto) {
        return getTaskService().createTaskQuery()
                .processDefinitionId(dto.getProcessInstanceId())
                .taskAssignee(dto.getAssignee())
                .list();
    }

    @Override
    public List<Task> taskById(TaskParamDTO dto) {
        return getTaskService().createTaskQuery()
                .processDefinitionId(dto.getProcessInstanceId())
                .list();
    }
}
