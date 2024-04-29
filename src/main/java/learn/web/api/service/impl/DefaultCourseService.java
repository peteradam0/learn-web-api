package learn.web.api.service.impl;

import learn.web.api.dao.CanvasDao;
import learn.web.api.dao.CourseDao;
import learn.web.api.exception.ServiceLayerException;
import learn.web.api.facade.dto.CanvasCourse;
import learn.web.api.model.Course;
import learn.web.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DefaultCourseService implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CanvasDao canvasDao;

    @Override
    public Course createCourse(Course course) {
        course.setCreatedAt(Instant.now());
        return courseDao.save(course);
    }

    @Override
    public List<Course> getCoursesForAdminUser(String userId) {
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

    @Override
    public List<Course> getAllPublishedCourses() {
        return courseDao.findCoursesByIsPublished(true);
    }

    @Override
    public List<CanvasCourse> getCourseSuggestions() {
        return canvasDao.findAllCanvasCourses();
    }

    @Override
    public void deleteCourse(String courseId) {
        Course course = courseDao.findCoursesById(courseId);

        if (course == null) {
            throw new ServiceLayerException("Course with id " + courseId + "not found, it will not be deleted");
        }

        courseDao.delete(course);
    }

}
