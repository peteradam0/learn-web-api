package learn.web.api.facades;

import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.dtos.WebhookUserData;

import java.util.List;

public interface UserFacade {
    public void createUser(WebhookUserData user);

    public UserData getCurrentUserData();

    void updateUser(WebhookUserData webhookUserData);

    UserData getUserDataById(String userId);

    List<UserData> getUsers();
}
