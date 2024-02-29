package qtx.spring.flowable.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.common.Result;
import qtx.spring.flowable.pojo.dto.ProcessDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.ProcessInstanceVO;
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
    public Result<DeploymentVO> createDeploy(@RequestParam("file") MultipartFile file, String name) {
        return Result.success(flowableBaseService.createDeploy(file, name));
    }

    @GetMapping("/deleteDeploy")
    public Result<Object> deleteDeploy(@RequestParam String deploymentId) {
        flowableBaseService.deleteDeploy(deploymentId);
        return Result.success();
    }

    @PostMapping("/startProcess")
    public Result<ProcessInstanceVO> startProcess(@RequestBody ProcessDTO dto) {
        return Result.success(flowableBaseService.startProcess(dto));
    }

    @PostMapping("/completeTask")
    public Result<Object> completeTask(@RequestBody ProcessDTO dto) {
        flowableBaseService.completeTask(dto);
        return Result.success();
    }

    @PostMapping("/delegate")
    public Result<Object> delegate(@RequestBody ProcessDTO dto) {
        flowableBaseService.delegate(dto);
        return Result.success();
    }

    @PostMapping("/claim")
    public Result<Object> claim(@RequestBody ProcessDTO dto) {
        flowableBaseService.claim(dto);
        return Result.success();
    }

    @PostMapping("/unClaim")
    public Result<Object> unClaim(@RequestBody ProcessDTO dto) {
        flowableBaseService.unClaim(dto);
        return Result.success();
    }

    @PostMapping("/setAssignee")
    public Result<Object> setAssignee(@RequestBody ProcessDTO dto) {
        flowableBaseService.setAssignee(dto);
        return Result.success();
    }

    @GetMapping("/getProcessDiagram")
    public void getProcessDiagram(HttpServletResponse httpServletResponse, @RequestParam String processInstanceId) {
        flowableBaseService.getProcessDiagram(httpServletResponse, processInstanceId);
    }
}