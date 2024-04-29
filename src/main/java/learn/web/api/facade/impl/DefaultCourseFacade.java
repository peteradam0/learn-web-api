package learn.web.api.facade.impl;

import learn.web.api.exception.FacadeLayerException;
import learn.web.api.facade.*;
import learn.web.api.facade.dto.*;
import learn.web.api.facade.populator.impl.*;
import learn.web.api.model.Course;
import learn.web.api.model.Organizations;
import learn.web.api.model.UserRole;
import learn.web.api.service.CourseService;
import learn.web.api.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private SessionService sessionService;

    @Autowired
    private OrganizationFacade organizationFacade;

    @Autowired
    private ChapterFacade chapterFacade;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private WebhookUserDataToUserPopulator webhookUserDataToUserPopulator;

    @Autowired
    private CourseParticipationFacade courseParticipationFacade;

    @Autowired
    private CanvasCourseToCourseSuggestionPopulator courseSuggestionPopulator;

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
    public List<CourseData> getCreatedCourseDataForAdmin() {

        String userId = sessionService.getCurrentUserId();
        //TODO: check if this user is actually an admin
        List<CourseData> courseDataListData = new ArrayList<>();
        for (Course course : courseService.getCoursesForAdminUser(userId)) {
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
    public List<CourseData> getSelfCourses() {

        List<Course> allPublishedCourses = courseService.getAllPublishedCourses();
        UserData userData = userFacade.getUserDataById(sessionService.getCurrentUserId());

        if (userData != null) {
            List<CourseData> courseDataList = new ArrayList<>();
            if (userData.getUserRole() == UserRole.ADMIN) {
                populateCourseDataForAdminUser(allPublishedCourses, courseDataList);

            } else {
                populateCourseDateForConsumer(userData, allPublishedCourses, courseDataList);

            }
            return courseDataList;

        } else {
            throw new FacadeLayerException("Current user not found, token missing");
        }
    }

    private void populateCourseDateForConsumer(UserData userData, List<Course> allPublishedCourses, List<CourseData> courseDataList) {
        for (Course course : allPublishedCourses) {
            OrganizationData courseOrganization = organizationFacade.getOrganizationByName(course.getOrganization().getName());
            if ((courseOrganization != null && courseOrganization.getMembers().contains(userData))
                    || Objects.equals((Objects.requireNonNull(course.getOrganization())).getName(), Organizations.Public.name()))
                populateCourseData(courseDataList, course);
        }
    }

    private void populateCourseDataForAdminUser(List<Course> allPublishedCourses, List<CourseData> courseDataList) {
        for (Course course : allPublishedCourses) {
            populateCourseData(courseDataList, course);
        }
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

        return getSelfCourses().stream()
                .filter(courseData -> courseIdList.contains(courseData.getId())).toList();
    }

    @Override
    public List<CourseSuggestionData> getCourseSuggestions() {

        List<CanvasCourse> canvasCourses = courseService.getCourseSuggestions();

        if (canvasCourses == null) {
            throw new FacadeLayerException("Canvas courses not found");
        }

        List<CourseSuggestionData> courseSuggestionDataList = new ArrayList<>();
        for (CanvasCourse course : canvasCourses) {
            CourseSuggestionData courseSuggestionData = new CourseSuggestionData();
            courseSuggestionPopulator.populate(course, courseSuggestionData);
            courseSuggestionDataList.add(courseSuggestionData);
        }
        return courseSuggestionDataList;

    }

    @Override
    public void deleteCourse(String courseId) {
        courseService.deleteCourse(courseId);
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

