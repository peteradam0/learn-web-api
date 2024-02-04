package learn.web.api.facade;

import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.ClerkEventData;

import java.util.List;

public interface UserFacade {
    public void createUser(ClerkEventData user);

    public UserData getCurrentUserData();

    void updateUser(ClerkEventData clerkEventData);

    UserData getUserDataById(String userId);

    UserData getUserDataEmail(String email);

    List<UserData> getUsers();

    void deleteUser(String id);
}
