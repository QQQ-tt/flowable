package qtx.spring.flowable.service.impl;

import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Service;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.dto.FinishedTaskQueryDTO;
import qtx.spring.flowable.pojo.dto.HistoryParamDTO;
import qtx.spring.flowable.pojo.dto.VariablesParamDTO;
import qtx.spring.flowable.pojo.vo.HistoryVO;
import qtx.spring.flowable.pojo.vo.TaskVO;
import qtx.spring.flowable.service.FlowableQueryHistoryService;

import java.util.*;

/**
 * @author qtx
 * @since 2024/2/28
 */
@Service
public class FlowableQueryHistoryServiceImpl extends FlowableFactory implements FlowableQueryHistoryService {
    @Override
    public List<HistoryVO> getHistoryList(HistoryParamDTO dto) {
        List<HistoryVO> voList = new ArrayList<>();
        List<HistoricActivityInstance> list = getHistoryService().createHistoricActivityInstanceQuery()
                .processDefinitionId(dto.getProcessDefinitionId())
                .processInstanceId(dto.getProcessInstanceId())
                .finished() // 查询的历史记录的状态是已经完成
                .orderByHistoricActivityInstanceEndTime()
                .asc() // 指定排序的字段和顺序
                .list();
        list.forEach(e -> {
            voList.add(HistoryVO.builder()
                    .getActivityId(e.getActivityId())
                    .activityName(e.getActivityName())
                    .assignee(e.getAssignee())
                    .durationInMillis(e.getDurationInMillis())
                    .build());
        });
        return voList;
    }

    @Override
    public Map<String, Object> processVariables(VariablesParamDTO dto) {
        Map<String, Object> map;
        // 流程变量
        HistoricTaskInstance historicTaskInstance = getHistoryService().createHistoricTaskInstanceQuery()
                .includeProcessVariables()
//                .processDefinitionId(dto.getProcessDefinitionId())
//                .processInstanceId(dto.getProcessInstanceId())
                .taskId(dto.getTaskId())
                //.finished()
                .singleResult();
        if (Objects.nonNull(historicTaskInstance)) {
            map = historicTaskInstance.getProcessVariables();
        } else {
            map = getTaskService().getVariables(dto.getTaskId());

        }
        return map;
    }

    @Override
    public List<TaskVO> finishedTaskList(FinishedTaskQueryDTO dto) {
        List<TaskVO> vos = new ArrayList<>();
        List<HistoricTaskInstance> list = getHistoryService().createHistoricTaskInstanceQuery()
                .includeProcessVariables()
                .processDefinitionName(dto.getName())
                .finished()
                .taskAssignee(dto.getAssignee())
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list();
        list.forEach(e -> {
            vos.add(TaskVO.builder().id(e.getId()).name(e.getName()).createTime(e.getCreateTime()).build());
        });
        return vos;
    }
}
