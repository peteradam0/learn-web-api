package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataPopulator implements Populator<Course, CourseData> {
    @Override
    public void populate(Course source, CourseData target) {
        source.setTitle(target.getTitle());
    }
}
