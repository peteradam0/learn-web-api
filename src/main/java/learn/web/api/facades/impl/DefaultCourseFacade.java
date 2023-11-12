package learn.web.api.facades.impl;

import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.dtos.CourseData;
import org.springframework.stereotype.Component;
@Component
public class DefaultCourseFacade implements CourseFacade {
    @Override
    public CourseData createCourse(CourseData courseData) {
        return null;
    }
}
