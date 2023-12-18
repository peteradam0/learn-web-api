package learn.web.api.services;

import learn.web.api.models.User;

import java.util.List;

public interface UserService {
    public void createUser(User user);

    User getUserById(String userId);

    void updateUser(User user);

    List<User> getUsers();

    void deleteUser(String id);

    User getUserByEmail(String email);
}
