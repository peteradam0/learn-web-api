package learn.web.api.facade;

import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.WebhookUserData;

import java.util.List;

public interface UserFacade {
    public void createUser(WebhookUserData user);

    public UserData getCurrentUserData();

    void updateUser(WebhookUserData webhookUserData);

    UserData getUserDataById(String userId);

    UserData getUserDataEmail(String email);

    List<UserData> getUsers();

    void deleteUser(String id);
}
