package qtx.spring.flowable.service;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;

/**
 * @author qtx
 * @since 2024/2/28
 */
public interface FlowableUserService {

    /**
     * 创建用户
     * @param userId 用户id
     * @param firstName 用户名
     * @param lastName 用户名
     * @param email 邮箱
     */
    void createUser(String userId, String firstName, String lastName, String email);

    /**
     * 更新用户
     * @param updatedUser 用户
     */
    void updateUser(User updatedUser);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void deleteUser(String userId);

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 用户
     */
    User findUserById(String userId);

    /**
     * 创建用户组
     * @param groupId 用户组id
     * @param groupName 用户组名
     */
    void createGroup(String groupId, String groupName);

    /**
     * 更新用户组
     * @param updatedGroup 用户组
     */
    void updateGroup(Group updatedGroup);

    /**
     * 删除用户组
     * @param groupId 用户组id
     */
    void deleteGroup(String groupId);

    /**
     * 根据用户组id查询用户组
     * @param groupId 用户组id
     * @return 用户组
     */
    Group findGroupById(String groupId);
}
