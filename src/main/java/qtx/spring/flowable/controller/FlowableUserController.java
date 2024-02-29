package qtx.spring.flowable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qtx.spring.flowable.common.Result;
import qtx.spring.flowable.pojo.vo.GroupVO;
import qtx.spring.flowable.pojo.vo.UserVO;
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

    @GetMapping("/saveOrUpdateUser")
    public Result<Object> saveOrUpdateUser(String userId, String firstName, String lastName, String email) {
        flowableUserSsrvice.saveOrUpdateUser(userId, firstName, lastName, email);
        return Result.success();
    }

    @GetMapping("/deleteUser")
    public Result<Object> deleteUser(String userId) {
        flowableUserSsrvice.deleteUser(userId);
        return Result.success();
    }

    @GetMapping("/findUserById")
    public Result<UserVO> findUserById(String userId) {
        return Result.success(flowableUserSsrvice.findUserById(userId));
    }

    @GetMapping("/findGroupById")
    public Result<GroupVO> findGroupById(String groupId) {
        return Result.success(flowableUserSsrvice.findGroupById(groupId));
    }

    @GetMapping("/saveOrUpdateGroup")
    public Result<Object> saveOrUpdateGroup(String groupId, String groupName) {
        flowableUserSsrvice.saveOrUpdateGroup(groupId, groupName);
        return Result.success();
    }

    @GetMapping("/deleteGroup")
    public Result<Object> deleteGroup(String groupId) {
        flowableUserSsrvice.deleteGroup(groupId);
        return Result.success();
    }

}
