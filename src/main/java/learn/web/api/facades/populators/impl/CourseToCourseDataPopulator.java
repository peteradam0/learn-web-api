package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataPopulator implements Populator<Course, CourseData> {
    @Override
    public void populate(Course source, CourseData target) {
            target.setTitle(source.getTitle());
            target.setId(source.getId());
            target.setCategory(source.getCategoryId());
            target.setImageUrl(source.getImageUrl());
            target.setDescription(source.getDescription());
            target.setPublished(source.isPublished());
    }
}
