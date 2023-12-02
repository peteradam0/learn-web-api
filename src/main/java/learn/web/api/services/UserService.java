package learn.web.api.services;

import learn.web.api.models.User;

public interface UserService {
    public void createUser(User user);

    User getUserById(String userId);

    void updateUser(User user);
}
