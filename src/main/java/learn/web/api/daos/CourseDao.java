package learn.web.api.daos;

import learn.web.api.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseDao extends MongoRepository<Course, String> {
}
