package learn.web.api.services.impl;

import learn.web.api.models.Course;
import learn.web.api.services.CourseService;
import org.springframework.stereotype.Component;

@Component
public class DefaultCourseService implements CourseService {
    @Override
    public Course createCourse(Course course) {
        return null;
    }
}
