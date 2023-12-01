package learn.web.api.facades;

import learn.web.api.facades.dtos.UserData;
import learn.web.api.facades.dtos.WebhookUserData;

public interface UserFacade {
    public void createUser(WebhookUserData user);

    public UserData getCurrentUserData();

}
