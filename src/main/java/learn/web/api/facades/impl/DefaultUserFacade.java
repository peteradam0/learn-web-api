package learn.web.api.facades.impl;

import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.UserFacade;
import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.dtos.WebhookUserData;
import learn.web.api.facades.populators.impl.UserDataToUserPopulator;
import learn.web.api.facades.populators.impl.UserToUserDataPopulator;
import learn.web.api.models.User;
import learn.web.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        if(user != null){
            userToUserDataPopulator.populate(user, userData);
        }
        return userData;
    }
}
