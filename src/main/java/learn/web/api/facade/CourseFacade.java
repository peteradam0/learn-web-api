package learn.web.api.facade;

import learn.web.api.facade.dto.*;

import java.util.List;

public interface CourseFacade {
    CourseCreateResponseData createCourse(CourseCreateRequestData courseCreateRequestData);
    List<CourseData> getCreatedCourseDataForAdmin();

    List<CourseData> getCourses();

    /**
     * Will return published courses, based on the role of the user.
     * @return list of CourseData
     */
    List<CourseData> getSelfCourses();

    //TODO: A course should be only editable by a admin or maintainer
    CourseData getCourseData(String courseId);

    void changePublication(String courseId);

    List<CourseData> getInProgressCourses();

    List<CourseSuggestionData> getCourseSuggestions(String canvasDomain);

    void deleteCourse(String courseId);

    List<CourseData> getNotInProgressCourses();
}
