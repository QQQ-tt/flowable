package qtx.spring.flowable.service.impl;

import io.minio.ObjectWriteResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.dto.ProcessDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.ProcessInstanceVO;
import qtx.spring.flowable.service.FlowableBaseService;
import qtx.spring.flowable.util.MinioUtils;
import qtx.spring.flowable.util.NumUtils;

/**
 * @author qtx
 * @since 2024/2/27
 */
@Service
public class FlowableBaseServiceImpl extends FlowableFactory implements FlowableBaseService {

    private final MinioUtils minioUtils;

    public FlowableBaseServiceImpl(MinioUtils minioUtils) {
        this.minioUtils = minioUtils;
    }

    @SneakyThrows
    @Override
    public DeploymentVO createDeploy(MultipartFile file, String name) {
        Deployment deployment = getRepositoryService()
                .createDeployment()
                .name(name)
                .addInputStream(file.getOriginalFilename(), file.getInputStream())
                .deploy();

        // 文件名
        String oldFileName = file.getOriginalFilename();
        String newFileName = NumUtils.uuid() + "." + StringUtils.substringAfterLast(oldFileName, ".");
        // 类型
        String contentType = file.getContentType();
        ObjectWriteResponse uploadFile = minioUtils.uploadFile(file, newFileName, contentType);
        return DeploymentVO.builder()
                .id(deployment.getId())
                .name(deployment.getName())
                .build();
    }

    @Override
    public ProcessInstanceVO startProcess(ProcessDTO dto) {
        ProcessInstance instance = getRuntimeService().startProcessInstanceById(dto.getProcessInstanceId(),
                dto.getVariables());
        return ProcessInstanceVO.builder()
                .id(instance.getId())
                .processDefinitionId(instance.getProcessDefinitionId())
                .processDefinitionName(instance.getProcessDefinitionName())
                .activityId(instance.getActivityId())
                .build();
    }

    @Override
    public void completeTask(ProcessDTO dto) {
        getTaskService().complete(dto.getTaskId(), dto.getVariables());
    }

    @Override
    public void delegate(ProcessDTO dto) {
        getTaskService().delegateTask(dto.getTaskId(), dto.getUser());
    }

    @Override
    public void claim(ProcessDTO dto) {
        getTaskService().claim(dto.getTaskId(), dto.getUser());
    }

    @Override
    public void unClaim(ProcessDTO dto) {
        getTaskService().unclaim(dto.getTaskId());
    }

    @Override
    public void setAssignee(ProcessDTO dto) {
        getTaskService().setAssignee(dto.getTaskId(), dto.getUser());
    }
}
