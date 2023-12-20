package learn.web.api.facade;

import learn.web.api.facade.dtos.*;

import java.util.List;

public interface CourseFacade {
    CourseCreateResponseData createCourse(CourseCreateRequestData courseCreateRequestData);
    List<CourseData> getCourseDataForUser();

    List<CourseData> getCourses();

    List<CourseData> getPublishedCourses();

    //TODO: A course should be only editable by a admin or maintainer
    CourseData getCourseData(String courseId);

    void changePublication(String courseId);

    List<CourseData> getInProgressCourses();
}
