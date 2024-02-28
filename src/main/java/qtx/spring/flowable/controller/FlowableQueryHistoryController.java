package qtx.spring.flowable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qtx.spring.flowable.service.FlowableQueryHistoryService;

/**
 * @author qtx
 * @since 2024/2/28
 */
@RestController
@RequestMapping("/flowableQueryHistory")
public class FlowableQueryHistoryController {

    private final FlowableQueryHistoryService flowableQueryHistoryService;

    public FlowableQueryHistoryController(FlowableQueryHistoryService flowableQueryHistoryService) {
        this.flowableQueryHistoryService = flowableQueryHistoryService;
    }
}
