package qtx.spring.flowable.controller;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.common.Result;
import qtx.spring.flowable.pojo.dto.ProcessDTO;
import qtx.spring.flowable.service.FlowableBaseService;

/**
 * @author qtx
 * @since 2024/2/27
 */
@RestController
@RequestMapping("/flowableBase")
public class FlowableBaseController {


    private final FlowableBaseService flowableBaseService;

    public FlowableBaseController(FlowableBaseService flowableBaseService) {
        this.flowableBaseService = flowableBaseService;
    }

    @GetMapping("/createDeploy")
    public Result<Deployment> createDeploy(@RequestParam("file") MultipartFile file, String name) {
        return Result.success(flowableBaseService.createDeploy(file, name));
    }

    @PostMapping("/startProcess")
    public Result<ProcessInstance> startProcess(@RequestBody ProcessDTO dto) {
        return Result.success(flowableBaseService.startProcess(dto));
    }

    @PostMapping("/completeTask")
    public Result<Object> completeTask(@RequestBody ProcessDTO dto) {
        flowableBaseService.completeTask(dto);
        return Result.success();
    }
}
