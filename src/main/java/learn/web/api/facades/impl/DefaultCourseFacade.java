package learn.web.api.facades.impl;

import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseCreateRequestData;
import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.impl.CourseDataToCoursePopulator;
import learn.web.api.facades.populators.impl.CourseToCourseDataPopulator;
import learn.web.api.facades.populators.impl.CourseToCourseDataResponsePopulator;
import learn.web.api.models.Course;
import learn.web.api.services.CourseService;
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
    private CourseToCourseDataPopulator courseToCourseDataPopulator;

    @Autowired
    private SessionFacade sessionFacade;

    @Autowired
    private CourseService courseService;

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
        for(Course course : courseService.getCoursesForUser(userId)){
            CourseData courseData = new CourseData();
            courseToCourseDataPopulator.populate(course,courseData);
            courseDataListData.add(courseData);
        }
        return courseDataListData;
    }

    @Override
    public List<CourseData> getCourses() {

        List<CourseData> courseDataListData = new ArrayList<>();
        for(Course course : courseService.getCourses()){
            CourseData courseData = new CourseData();
            courseToCourseDataPopulator.populate(course,courseData);
            courseDataListData.add(courseData);
        }
        return courseDataListData;
    }
}

