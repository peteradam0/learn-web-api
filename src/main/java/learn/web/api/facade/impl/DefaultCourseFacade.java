package learn.web.api.facade.impl;

import learn.web.api.facade.*;
import learn.web.api.facade.dto.*;
import learn.web.api.facade.populator.impl.CourseDataToCoursePopulator;
import learn.web.api.facade.populator.impl.CourseToCourseDataPopulator;
import learn.web.api.facade.populator.impl.CourseToCourseDataResponsePopulator;
import learn.web.api.facade.populator.impl.UserDataToCourseAuthorData;
import learn.web.api.model.Course;
import learn.web.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultCourseFacade implements CourseFacade {

    @Autowired
    private CourseDataToCoursePopulator courseDataToCoursePopulator;

    @Autowired
    private CourseToCourseDataResponsePopulator courseToCourseDataResponsePopulator;

    @Autowired
    private UserDataToCourseAuthorData userDataToCourseAuthorData;

    @Autowired
    private CourseToCourseDataPopulator courseToCourseDataPopulator;

    @Autowired
    private SessionFacade sessionFacade;

    @Autowired
    private ChapterFacade chapterFacade;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private CourseParticipationFacade courseParticipationFacade;

    @Override
    public CourseCreateResponseData createCourse(CourseCreateRequestData courseCreateRequestData) {

        Course course = new Course();
        courseDataToCoursePopulator.populate(courseCreateRequestData, course);

        Course createdCourse = courseService.createCourse(course);

        CourseCreateResponseData response = new CourseCreateResponseData();
        courseToCourseDataResponsePopulator.populate(createdCourse, response);
        return response;
    }

    @Override
    public List<CourseData> getCourseDataForUser() {

        String userId = sessionFacade.getCurrentUserId();

        List<CourseData> courseDataListData = new ArrayList<>();
        for (Course course : courseService.getCoursesForUser(userId)) {
            CourseData courseData = new CourseData();
            courseToCourseDataPopulator.populate(course, courseData);
            courseDataListData.add(courseData);
        }
        return courseDataListData;
    }

    @Override
    public List<CourseData> getCourses() {

        List<CourseData> courseDataListData = new ArrayList<>();
        for (Course course : courseService.getCourses()) {
            populateCourseData(courseDataListData, course);
        }
        return courseDataListData;
    }

    @Override
    public List<CourseData> getPublishedCourses() {
        List<CourseData> courseDataListData = new ArrayList<>();
        for (Course course : courseService.getPublishedCourses()) {
            populateCourseData(courseDataListData, course);
        }
        return courseDataListData;
    }

    @Override
    public CourseData getCourseData(String courseId) {
        Course course = courseService.getCourse(courseId);
        CourseAuthorData courseAuthorData = getCourseAuthorData(course);
        CourseData courseData = new CourseData();
        courseData.setCourseAuthorData(courseAuthorData);
        if (course != null) {
            courseToCourseDataPopulator.populate(course, courseData);
        }

        List<ChapterData> chapterData = new ArrayList<>();
        chapterData = chapterFacade.getChapterData(courseId);
        courseData.setChapterData(chapterData);

        return courseData;
    }

    @Override
    public void changePublication(String courseId) {
        courseService.changePublication(courseId);
    }

    @Override
    public List<CourseData> getInProgressCourses() {
        List<String> courseIdList = courseParticipationFacade.getAllParticipations().stream()
                .map(CourseParticipationData::getCourseId).toList();

        return getCourses().stream()
                .filter(courseData -> courseIdList.contains(courseData.getId())).toList();
    }

    private void populateCourseData(List<CourseData> courseDataListData, Course course) {
        CourseAuthorData courseAuthorData = getCourseAuthorData(course);

        CourseData courseData = new CourseData();
        courseData.setCourseAuthorData(courseAuthorData);
        courseToCourseDataPopulator.populate(course, courseData);
        courseDataListData.add(courseData);
    }

    private CourseAuthorData getCourseAuthorData(Course course) {
        UserData userData = userFacade.getUserDataById(course.getUserId());
        CourseAuthorData courseAuthorData = new CourseAuthorData();
        userDataToCourseAuthorData.populate(userData, courseAuthorData);
        return courseAuthorData;
    }
}

