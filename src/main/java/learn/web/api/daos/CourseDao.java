package learn.web.api.daos;

import learn.web.api.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseDao extends MongoRepository<Course, String> {

    List<Course> findByUserId(String userId);

    Course findCoursesById(String courseId);
}
