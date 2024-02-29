package qtx.spring.flowable.service;

import qtx.spring.flowable.pojo.dto.FinishedTaskQueryDTO;
import qtx.spring.flowable.pojo.dto.HistoryParamDTO;
import qtx.spring.flowable.pojo.dto.VariablesParamDTO;
import qtx.spring.flowable.pojo.vo.HistoryVO;
import qtx.spring.flowable.pojo.vo.TaskVO;

import java.util.List;
import java.util.Map;

/**
 * @author qtx
 * @since 2024/2/28
 */
public interface FlowableQueryHistoryService {
    /**
     * 获取流程任务的历史数据
     * @param dto
     * @return
     */
    List<HistoryVO> getHistoryList(HistoryParamDTO dto);

    /**
     * 获取流程变量
     * @param dto
     * @return
     */
    Map<String, Object> processVariables(VariablesParamDTO dto);

    /**
     * 已办任务
     * @param dto
     * @return
     */
    List<TaskVO> finishedTaskList(FinishedTaskQueryDTO dto);
}
