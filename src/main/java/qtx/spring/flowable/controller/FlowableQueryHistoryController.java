package qtx.spring.flowable.controller;

import org.flowable.engine.history.HistoricActivityInstance;
import org.springframework.web.bind.annotation.*;
import qtx.spring.flowable.common.Result;
import qtx.spring.flowable.pojo.dto.FinishedTaskQueryDTO;
import qtx.spring.flowable.pojo.dto.HistoryParamDTO;
import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.pojo.dto.VariablesParamDTO;
import qtx.spring.flowable.pojo.vo.HistoryVO;
import qtx.spring.flowable.pojo.vo.TaskVO;
import qtx.spring.flowable.service.FlowableQueryHistoryService;

import java.util.List;
import java.util.Map;

/**
 * @author qtx
 * @since 2024/2/28
 */
@RestController
@RequestMapping("/flowableQueryHistory")
public class FlowableQueryHistoryController {

    private final FlowableQueryHistoryService flowableQueryHistoryService;

    public FlowableQueryHistoryController(FlowableQueryHistoryService flowableQueryHistoryService) {
        this.flowableQueryHistoryService = flowableQueryHistoryService;
    }

    @PostMapping("/getHistoryList")
    public Result<List<HistoryVO>> getHistoryList(@RequestBody HistoryParamDTO dto) {
        return Result.success(flowableQueryHistoryService.getHistoryList(dto));
    }

    @PostMapping("/processVariables")
    public Result<Map<String, Object>> processVariables(VariablesParamDTO dto) {
        return Result.success(flowableQueryHistoryService.processVariables(dto));
    }

    @PostMapping("/finishedTaskList")
    public Result<List<TaskVO>> finishedTaskList(FinishedTaskQueryDTO dto) {
        return Result.success(flowableQueryHistoryService.finishedTaskList(dto));
    }


}
