package qtx.spring.flowable.service;

import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.pojo.dto.ProcessDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.ProcessInstanceVO;

/**
 * @author qtx
 * @since 2024/2/27
 */
public interface FlowableBaseService {

    /**
     * 创建部署
     *
     * @param file 流程文件
     * @param name 流程名称
     * @return
     */
    DeploymentVO createDeploy(MultipartFile file, String name);

    /**
     * 运行流程
     *
     * @param dto 流程参数
     * @return 流程实例
     */
    ProcessInstanceVO startProcess(ProcessDTO dto);

    /**
     * 完成任务
     *
     * @param dto 流程参数
     */
    void completeTask(ProcessDTO dto);

    /**
     * 委派任务
     *
     * @param dto 流程参数
     */
    void delegate(ProcessDTO dto);

    /**
     * 认领任务
     *
     * @param dto 流程参数
     */
    void claim(ProcessDTO dto);

    /**
     * 取消认领任务
     *
     * @param dto 流程参数
     */
    void unClaim(ProcessDTO dto);

    /**
     * 设置任务的执行人
     *
     * @param dto 流程参数
     */
    void setAssignee(ProcessDTO dto);
}
