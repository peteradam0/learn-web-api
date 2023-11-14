package learn.web.api.facades;

import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseData;

public interface CourseFacade {
    CourseCreateResponseData createCourse(CourseData courseData);
}
