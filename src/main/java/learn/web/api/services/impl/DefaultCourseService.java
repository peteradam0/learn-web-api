package learn.web.api.services.impl;

import learn.web.api.daos.CourseDao;
import learn.web.api.models.Course;
import learn.web.api.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCourseService implements CourseService {

    @Autowired
    private CourseDao courseDao;


    @Override
    public Course createCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public List<Course> getCoursesForUser(String userId) {
        return courseDao.findByUserId(userId);
    }
}
