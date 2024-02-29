package qtx.spring.flowable.service.impl;

import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;
import qtx.spring.flowable.common.FlowableFactory;
import qtx.spring.flowable.service.FlowableUserService;

/**
 * @author qtx
 * @since 2024/2/28
 */
@Service
public class FlowableUserServiceImpl extends FlowableFactory implements FlowableUserService {


    @Override
    public void createUser(String userId, String firstName, String lastName, String email) {
        User user = getIdentityService().newUser(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        getIdentityService().saveUser(user);
    }

    @Override
    public void updateUser(User updatedUser) {
        getIdentityService().saveUser(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {
        getIdentityService().deleteUser(userId);
    }

    @Override
    public User findUserById(String userId) {
        return getIdentityService().createUserQuery()
                .userId(userId)
                .singleResult();
    }

    @Override
    public void createGroup(String groupId, String groupName) {
        Group group = getIdentityService().newGroup(groupId);
        group.setName(groupName);
        getIdentityService().saveGroup(group);
    }

    @Override
    public void updateGroup(Group updatedGroup) {
        getIdentityService().saveGroup(updatedGroup);
    }

    @Override
    public void deleteGroup(String groupId) {
        getIdentityService().deleteGroup(groupId);
    }

    @Override
    public Group findGroupById(String groupId) {
        return getIdentityService().createGroupQuery().groupId(groupId).singleResult();
    }
}
