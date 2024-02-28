package qtx.spring.flowable.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qtx.spring.flowable.service.FlowableUserService;

/**
 * @author qtx
 * @since 2024/2/28
 */
@RestController
@RequestMapping("/flowableUser")
public class FlowableUserController {

    private final FlowableUserService flowableUserSsrvice;

    public FlowableUserController(FlowableUserService flowableUserSsrvice) {
        this.flowableUserSsrvice = flowableUserSsrvice;
    }
}
