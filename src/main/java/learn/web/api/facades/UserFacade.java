package learn.web.api.facades;

import learn.web.api.facades.dtos.UserData;

public interface UserFacade {
    public void createUser(UserData user);
}
