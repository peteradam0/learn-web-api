package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseDataToCoursePopulator implements Populator<CourseData, Course> {
    @Override
    public void populate(CourseData source, Course target) {

    }
}
