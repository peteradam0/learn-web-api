package learn.web.api.facades.impl;

import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.impl.CourseDataToCoursePopulator;
import learn.web.api.facades.populators.impl.CourseToCourseDataResponsePopulator;
import learn.web.api.models.Course;
import learn.web.api.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultCourseFacade implements CourseFacade {

    @Autowired
    private CourseDataToCoursePopulator courseDataToCoursePopulator;

    @Autowired
    private CourseToCourseDataResponsePopulator courseToCourseDataResponsePopulator;

    @Autowired
    private CourseService courseService;

    @Override
    public CourseCreateResponseData createCourse(CourseData courseData) {

        Course course = new Course();
        courseDataToCoursePopulator.populate(courseData, course);

        Course createdCourse = courseService.createCourse(course);

        CourseCreateResponseData response = new CourseCreateResponseData();
        courseToCourseDataResponsePopulator.populate(createdCourse, response);
        return response;
    }
}

