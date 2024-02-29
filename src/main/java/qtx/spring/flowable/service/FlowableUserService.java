package qtx.spring.flowable.service;

import qtx.spring.flowable.pojo.vo.GroupVO;
import qtx.spring.flowable.pojo.vo.UserVO;

/**
 * @author qtx
 * @since 2024/2/28
 */
public interface FlowableUserService {

    /**
     * 编辑用户
     * @param userId 用户id
     * @param firstName 用户名
     * @param lastName 用户名
     * @param email 邮箱
     */
    void saveOrUpdateUser(String userId, String firstName, String lastName, String email);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void deleteUser(String userId);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return 用户
     */
    UserVO findUserById(String userId);

    /**
     * 编辑用户组
     * @param groupId 用户组id
     * @param groupName 用户组名
     */
    void saveOrUpdateGroup(String groupId, String groupName);

    /**
     * 删除用户组
     * @param groupId 用户组id
     */
    void deleteGroup(String groupId);

    /**
     * 根据用户组id查询用户组
     *
     * @param groupId 用户组id
     * @return 用户组
     */
    GroupVO findGroupById(String groupId);
}
