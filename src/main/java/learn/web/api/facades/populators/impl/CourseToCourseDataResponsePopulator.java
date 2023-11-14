package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataResponsePopulator implements Populator<Course, CourseCreateResponseData> {
    @Override
    public void populate(Course source, CourseCreateResponseData target) {
        target.setTitle(source.getTitle());
        target.setId(source.getId());
    }
}
