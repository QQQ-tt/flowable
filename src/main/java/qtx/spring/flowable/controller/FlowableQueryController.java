package qtx.spring.flowable.controller;

import org.flowable.task.api.Task;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qtx.spring.flowable.common.Result;
import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.service.FlowableQueryService;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@RestController
@RequestMapping("/flowableQuery")
public class FlowableQueryController {

    private final FlowableQueryService flowableQueryService;

    public FlowableQueryController(FlowableQueryService flowableQueryService) {
        this.flowableQueryService = flowableQueryService;
    }

    @PostMapping("/taskByUserAndId")
    public Result<List<Task>> taskByUserAndId(@RequestBody TaskParamDTO dto) {
        return Result.success(flowableQueryService.taskByUserAndId(dto));
    }

    @PostMapping("/taskById")
    public Result<List<Task>> taskById(@RequestBody TaskParamDTO dto) {
        return Result.success(flowableQueryService.taskById(dto));
    }
}