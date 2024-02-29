package qtx.spring.flowable.service.impl;

import io.minio.ObjectWriteResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.image.impl.DefaultProcessDiagramGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.dto.ProcessDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.ProcessInstanceVO;
import qtx.spring.flowable.service.FlowableBaseService;
import qtx.spring.flowable.util.MinioUtils;
import qtx.spring.flowable.util.NumUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public void deleteDeploy(String deploymentId) {
        getRepositoryService().deleteDeployment(deploymentId, true);
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

    @SneakyThrows
    @Override
    public void getProcessDiagram(HttpServletResponse httpServletResponse, String processInstanceId) {
        // 1.根据流程定义ID获取流程实例ID
        ProcessInstance processInstance = getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        BpmnModel bpmnModel = getRepositoryService().getBpmnModel(processInstance.getProcessDefinitionId());
        // 2.获取高亮节点和线
        List<String> highLightedActivities = new ArrayList<>();
        List<String> highLightedFlows = new ArrayList<>();
        // 根据流程实例ID获得完成状态的activity
        List<HistoricActivityInstance> highLightedActivityList = getHistoryService().createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).finished().orderByHistoricActivityInstanceStartTime().asc().list();
        // 拿到ActivityId和sequenceFlow合集
        for (HistoricActivityInstance tempActivity : highLightedActivityList) {
            // 过滤掉因为边界事件删除掉人工节点（说明该人工节点实际未执行过，直接被边界事件结束了）
            String deleteReason = tempActivity.getDeleteReason();
            if (deleteReason != null && "userTask".equals(tempActivity.getActivityType()) && deleteReason.startsWith("boundary event")) {
                continue;
            }
            String activityId = tempActivity.getActivityId();
            highLightedActivities.add(activityId);
            if ("sequenceFlow".equals(tempActivity.getActivityType())) {
                highLightedFlows.add(tempActivity.getActivityId());
            }
        }
        // 3.生成图片
        ProcessEngineConfiguration engConf = this.getProcessEngine().getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engConf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivities, highLightedFlows, engConf.getActivityFontName(),
                engConf.getLabelFontName(), engConf.getAnnotationFontName(), engConf.getClassLoader(), 1.0, true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int length;
        out = httpServletResponse.getOutputStream();
        while ((length = in.read(buf)) != -1) {
            out.write(buf, 0, length);
        }
        IoUtil.closeSilently(out);
        IoUtil.closeSilently(in);

    }
}
