package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dto.CourseCreateResponseData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataResponsePopulator implements Populator<Course, CourseCreateResponseData> {
    @Override
    public void populate(Course source, CourseCreateResponseData target) {
        target.setTitle(source.getTitle());
        target.setId(source.getId());
    }
}
