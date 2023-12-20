package learn.web.api.facade.populator.impl;

import learn.web.api.facade.SessionFacade;
import learn.web.api.facade.dtos.CourseCreateRequestData;
import learn.web.api.facade.populator.Populator;
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
        target.setVideoUrl(source.getVideoUrl());
        target.setCategoryId(source.getCategory());
    }
}
