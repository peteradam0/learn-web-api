package learn.web.api.services.impl;

import learn.web.api.daos.UserDao;
import learn.web.api.models.User;
import learn.web.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
