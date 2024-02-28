package qtx.spring.flowable.service;

import qtx.spring.flowable.pojo.dto.TaskParamDTO;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.pojo.vo.TaskVO;

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
    List<TaskVO> taskByUserAndId(TaskParamDTO dto);

    /**
     * 根据流程ID查询任务
     *
     * @param dto 任务参数
     * @return 任务集合
     */
    List<TaskVO> taskById(TaskParamDTO dto);

    /**
     * 任务查询
     * @param dto 任务参数
     * @return 任务集合
     */
    List<TaskVO> taskList(TaskParamDTO dto);

    /**
     * 流程查询
     *
     * @return 流程集合
     */
    List<DeploymentVO> processAll();
}
