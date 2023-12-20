package learn.web.api.facade.populator.impl;

import learn.web.api.facade.ChapterFacade;
import learn.web.api.facade.dto.CourseData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDataPopulator implements Populator<Course, CourseData> {

    @Autowired
    private ChapterFacade chapterFacade;
    @Override
    public void populate(Course source, CourseData target) {
            target.setTitle(source.getTitle());
            target.setId(source.getId());
            target.setCategory(source.getCategoryId());
            target.setImageUrl(source.getImageUrl());
            target.setDescription(source.getDescription());
            target.setPublished(source.isPublished());
            target.setVideoUrl(source.getVideoUrl());
            target.setCreatedAt(String.valueOf(source.getCreatedAt()));
            target.setChapterData(chapterFacade.getChapterData(source.getId()));
    }
}
