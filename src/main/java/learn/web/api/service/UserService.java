package learn.web.api.service;

import learn.web.api.model.User;

import java.util.List;

public interface UserService {
    public void createUser(User user);

    User getUserById(String userId);

    void updateUser(User user);

    List<User> getUsers();

    void deleteUser(String id);

    User getUserByEmail(String email);
}
