package learn.web.api.service;

import learn.web.api.facade.dto.CanvasCourse;
import learn.web.api.model.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);

    List<Course> getCoursesForAdminUser(String userId);

    List<Course> getCourses();

    Course getCourse(String courseId);

    void changePublication(String courseId);

    List<Course> getAllPublishedCourses();

    List<CanvasCourse> getCourseSuggestions(String canvasDomain);

    void deleteCourse(String courseId);
}
