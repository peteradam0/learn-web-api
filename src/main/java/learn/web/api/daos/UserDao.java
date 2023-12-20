package learn.web.api.daos;

import learn.web.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User, String> {
    User findByClerkId(String clerkId);
    User findUserByEmail(String email);

}
