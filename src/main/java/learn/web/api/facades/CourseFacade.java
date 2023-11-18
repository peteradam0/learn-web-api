package learn.web.api.facades;

import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseCreateRequestData;
import learn.web.api.facades.dtos.CourseData;

import java.util.List;

public interface CourseFacade {
    CourseCreateResponseData createCourse(CourseCreateRequestData courseCreateRequestData);
    List<CourseData> getCourseDataForUser();
}
