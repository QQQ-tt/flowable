package qtx.spring.flowable.service;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;
import qtx.spring.flowable.pojo.dto.ProcessDTO;

/**
 * @author qtx
 * @since 2024/2/27
 */
public interface FlowableBaseService  {

    /**
     * 创建部署
     *
     * @param file 流程文件
     * @param name 流程名称
     * @return
     */
    Deployment createDeploy(MultipartFile file, String name);

    /**
     * 运行流程
     *
     * @param dto 流程参数
     * @return 流程实例
     */
    ProcessInstance startProcess(ProcessDTO dto);

    /**
     * 完成任务
     *
     * @param dto 流程参数
     */
    void completeTask(ProcessDTO dto);
}
