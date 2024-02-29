package qtx.spring.flowable.service.impl;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.pojo.vo.GroupVO;
import qtx.spring.flowable.pojo.vo.UserVO;
import qtx.spring.flowable.service.FlowableUserService;

/**
 * @author qtx
 * @since 2024/2/28
 */
@Service
public class FlowableUserServiceImpl extends FlowableFactory implements FlowableUserService {


    @Override
    public void saveOrUpdateUser(String userId, String firstName, String lastName, String email) {
        User user = getIdentityService().newUser(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        getIdentityService().saveUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        getIdentityService().deleteUser(userId);
    }

    @Override
    public UserVO findUserById(String userId) {
        User user = getIdentityService().createUserQuery()
                .userId(userId)
                .singleResult();
        if (user != null) {
            return UserVO.builder()
                    .userId(user.getId())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .build();
        }
        return null;
    }

    @Override
    public void saveOrUpdateGroup(String groupId, String groupName) {
        Group group = getIdentityService().newGroup(groupId);
        group.setName(groupName);
        getIdentityService().saveGroup(group);
    }

    @Override
    public void deleteGroup(String groupId) {
        getIdentityService().deleteGroup(groupId);
    }

    @Override
    public GroupVO findGroupById(String groupId) {
        Group group = getIdentityService().createGroupQuery()
                .groupId(groupId)
                .singleResult();
        if (group != null) {
            return GroupVO.builder()
                    .groupId(group.getId())
                    .groupName(group.getName())
                    .build();
        }
        return null;
    }
}
