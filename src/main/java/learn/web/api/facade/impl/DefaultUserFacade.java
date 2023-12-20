package learn.web.api.facade.impl;

import learn.web.api.facade.SessionFacade;
import learn.web.api.facade.UserFacade;
import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.WebhookUserData;
import learn.web.api.facade.populator.impl.UserDataToUserPopulator;
import learn.web.api.facade.populator.impl.UserToUserDataPopulator;
import learn.web.api.model.User;
import learn.web.api.model.UserRole;
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
    private SessionFacade sessionFacade;

    @Autowired
    private UserToUserDataPopulator userToUserDataPopulator;

    @Autowired
    private UserDataToUserPopulator userDataToUserPopulator;

    @Override
    public void createUser(WebhookUserData webhookUserData) {
        User user = new User();
        userDataToUserPopulator.populate(webhookUserData, user);
        user.setUserRole(UserRole.CUSTOMER);
        userService.createUser(user);
    }

    @Override
    public UserData getCurrentUserData() {
        User user = userService.getUserById(sessionFacade.getCurrentUserId());
        UserData userData = new UserData();
        userToUserDataPopulator.populate(user, userData);
        return userData;
    }

    @Override
    public void updateUser(WebhookUserData webhookUserData) {
        User user = new User();
        userDataToUserPopulator.populate(webhookUserData, user);
        userService.updateUser(user);
    }

    @Override
    public UserData getUserDataById(String userId) {
        User user = userService.getUserById(userId);
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
}
