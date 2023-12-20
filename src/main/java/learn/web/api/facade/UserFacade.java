package learn.web.api.facade;

import learn.web.api.facade.dtos.UserData;
import learn.web.api.facade.dtos.WebhookUserData;

import java.util.List;

public interface UserFacade {
    public void createUser(WebhookUserData user);

    public UserData getCurrentUserData();

    void updateUser(WebhookUserData webhookUserData);

    UserData getUserDataById(String userId);

    List<UserData> getUsers();

    void deleteUser(String id);
}
