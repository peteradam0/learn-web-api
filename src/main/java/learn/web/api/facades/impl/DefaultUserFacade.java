package learn.web.api.facades.impl;

import learn.web.api.facades.UserFacade;
import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.populators.impl.UserDataToUserPopulator;
import learn.web.api.models.User;
import learn.web.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDataToUserPopulator userDataToUserPopulator;

    @Override
    public void createUser(UserData userData) {
        User user = new User();
        userDataToUserPopulator.populate(userData, user);
        userService.createUser(user);
    }
}
