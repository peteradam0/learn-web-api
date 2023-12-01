package learn.web.api.facades;

import learn.web.api.facades.dtos.*;

import java.util.List;

public interface CourseFacade {
    CourseCreateResponseData createCourse(CourseCreateRequestData courseCreateRequestData);
    List<CourseData> getCourseDataForUser();

    List<CourseData> getCourses();

    List<CourseData> getPublishedCourses();

    //TODO: A course should be only editable by a admin or maintainer
    CourseData getCourseData(String courseId);

    void changePublication(String courseId);
}
