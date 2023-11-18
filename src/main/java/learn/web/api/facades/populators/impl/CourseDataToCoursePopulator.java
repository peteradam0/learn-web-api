package learn.web.api.facades.populators.impl;

import learn.web.api.facades.SessionFacade;
import learn.web.api.facades.dtos.CourseCreateRequestData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseDataToCoursePopulator implements Populator<CourseCreateRequestData, Course> {

    @Autowired
    private SessionFacade sessionFacade;
    @Override
    public void populate(CourseCreateRequestData source, Course target) {
        target.setTitle(source.getTitle());
        target.setUserId(sessionFacade.getCurrentUserId());
        target.setDescription(source.getDescription());
        target.setImageUrl(source.getImageUrl());
        target.setCategoryId(source.getCategory());
    }
}
