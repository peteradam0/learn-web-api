package learn.web.api.facades.populators.impl;

import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.dtos.CourseData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseDataToCoursePopulator implements Populator<CourseData, Course> {

    @Autowired
    private SessionFacade sessionFacade;
    @Override
    public void populate(CourseData source, Course target) {
        target.setTitle(source.getTitle());
        target.setUserId(sessionFacade.getCurrentUserId());
        target.setDescription(source.getDescription());
        target.setImageUrl(source.getImageUrl());
        target.setCategoryId(source.getCategory());
    }
}
