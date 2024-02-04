package learn.web.api.service.impl;

import learn.web.api.dao.UserDao;
import learn.web.api.model.User;
import learn.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userDao.findByClerkId(userId);
    }

    @Override
    public void updateUser(User user) {
        User foundUser = userDao.findUserByEmail(user.getEmail());
        if (foundUser != null) {
            userDao.delete(foundUser);
        }

        userDao.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void deleteUser(String id) {
        User user = userDao.findByClerkId(id);
        userDao.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
