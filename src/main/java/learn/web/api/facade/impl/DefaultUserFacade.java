package learn.web.api.facade.impl;

import learn.web.api.facade.UserFacade;
import learn.web.api.facade.dto.ClerkEventData;
import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.UserRoleChangeData;
import learn.web.api.facade.populator.impl.UserToUserDataPopulator;
import learn.web.api.facade.populator.impl.WebhookUserDataToUserPopulator;
import learn.web.api.model.User;
import learn.web.api.model.UserRole;
import learn.web.api.service.EmailService;
import learn.web.api.service.SessionService;
import learn.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserToUserDataPopulator userToUserDataPopulator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private WebhookUserDataToUserPopulator webhookUserDataToUserPopulator;

    @Override
    public void createUser(ClerkEventData clerkEventData) {
        User user = new User();
        webhookUserDataToUserPopulator.populate(clerkEventData, user);
        user.setUserRole(UserRole.CONSUMER);
        userService.createUser(user);
    }

    @Override
    public UserData getCurrentUserData() {
        User user = userService.getUserByClerkId(sessionService.getCurrentUserId());
        UserData userData = new UserData();
        userToUserDataPopulator.populate(user, userData);
        return userData;
    }

    @Override
    public void updateUser(ClerkEventData clerkEventData) {
        User user = new User();
        webhookUserDataToUserPopulator.populate(clerkEventData, user);
        userService.updateUser(user);
    }

    @Override
    public void updateUserRole(String userId, UserRoleChangeData userRoleChangeData) {
        User user = userService.getUserById(userId);
        user.setUserRole(UserRole.valueOf(userRoleChangeData.getRole()));
        userService.updateUser(user);
    }

    @Override
    public UserData getUserDataById(String userId) {
        User user = userService.getUserByClerkId(userId);
        UserData userData = new UserData();
        if (user != null) {
            userToUserDataPopulator.populate(user, userData);
        }
        return userData;
    }

    @Override
    public UserData getUserDataEmail(String email) {
        User user = userService.getUserByEmail(email);
        UserData userData = new UserData();
        if (user != null) {
            userToUserDataPopulator.populate(user, userData);
        }
        return userData;
    }

    @Override
    public List<UserData> getUsers() {
        List<UserData> userDataLister = new ArrayList<>();

        for (User user : userService.getUsers()) {
            UserData userData = new UserData();
            userToUserDataPopulator.populate(user, userData);
            userDataLister.add(userData);
        }
        return userDataLister;
    }

    @Override
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    @Override
    public void sendUserInvite(String email) {
        emailService.sendUserInvite(email);
    }
}
