package qtx.spring.flowable;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.history.HistoricActivityInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import qtx.spring.flowable.pojo.vo.DeploymentVO;
import qtx.spring.flowable.service.FlowableQueryService;

import java.util.List;

/**
 * @author qtx
 * @since 2024/2/27
 */
@SpringBootTest(classes = FlowableApplication.class)
@Slf4j
public class ServiceQueryTest {

    @Autowired
    private FlowableQueryService flowableQueryService;

    @Autowired
    private HistoryService historyService;

    @Test
    void testProcessAll() {
        List<DeploymentVO> processAll = flowableQueryService.processAll();
        System.out.println(processAll);
    }

    /**
     * 获取流程任务的历史数据
     */
    @Test
    public void testHistory() {

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processDefinitionId("leave:2:28c469f1-d6cb-11ee-bed8-00ff19dbe499")
                .finished() // 查询的历史记录的状态是已经完成
                .orderByHistoricActivityInstanceEndTime().asc() // 指定排序的字段和顺序
                .list();
        for (HistoricActivityInstance history : list) {
            log.info(history.getActivityName() + ":" + history.getAssignee() + "--"
                    + history.getActivityId() + ":" + history.getDurationInMillis() + "毫秒");
        }

    }

}
