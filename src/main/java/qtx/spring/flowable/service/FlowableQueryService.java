package qtx.spring.flowable.service;

import org.flowable.task.api.Task;
import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
public interface FlowableQueryService {

    /**
     * 根据用户和流程id任务查询
     *
     * @param dto 任务参数
     * @return 任务集合
     */
    List<Task> taskByUserAndId(TaskParamDTO dto);

    /**
     * 根据流程ID查询任务
     *
     * @param dto 任务参数
     * @return 任务集合
     */
    List<Task> taskById(TaskParamDTO dto);

    /**
     * 流程查询
     *
     * @return 流程集合
     */
    List<DeploymentVO> processAll();
}
