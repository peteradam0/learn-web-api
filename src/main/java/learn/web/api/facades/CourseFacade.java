package learn.web.api.facades;

import learn.web.api.facades.dtos.CourseData;

public interface CourseFacade {
    CourseData createCourse(CourseData courseData);
}
