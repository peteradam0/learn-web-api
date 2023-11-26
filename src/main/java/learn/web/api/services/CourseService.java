package learn.web.api.services;

import learn.web.api.models.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);

    List<Course> getCoursesForUser(String userId);

    List<Course> getCourses();

    Course getCourse(String courseId);

    void changePublication(String courseId);
}
