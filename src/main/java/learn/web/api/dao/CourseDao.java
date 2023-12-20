package learn.web.api.dao;

import learn.web.api.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseDao extends MongoRepository<Course, String> {

    List<Course> findByUserId(String userId);

    Course findCoursesById(String courseId);

    List<Course> findCoursesByIsPublished(boolean isPublished);
 }
