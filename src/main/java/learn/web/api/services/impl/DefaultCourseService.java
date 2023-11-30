package learn.web.api.services.impl;

import learn.web.api.daos.CourseDao;
import learn.web.api.models.Course;
import learn.web.api.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DefaultCourseService implements CourseService {

    @Autowired
    private CourseDao courseDao;


    @Override
    public Course createCourse(Course course) {
        course.setCreatedAt(Instant.now());
        return courseDao.save(course);
    }

    @Override
    public List<Course> getCoursesForUser(String userId) {
        return courseDao.findByUserId(userId);
    }

    @Override
    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourse(String courseId) {
        return courseDao.findCoursesById(courseId);
    }

    @Override
    public void changePublication(String courseId) {
        Course course = courseDao.findCoursesById(courseId);
        course.setPublished(!course.isPublished());
        courseDao.save(course);
    }

}
