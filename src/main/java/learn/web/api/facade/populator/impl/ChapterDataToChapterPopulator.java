package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.ChapterData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.models.Chapter;
import org.springframework.stereotype.Component;

@Component
public class ChapterDataToChapterPopulator implements Populator<ChapterData, Chapter> {
    @Override
    public void populate(ChapterData source, Chapter target) {
        target.setId(source.getId());
        target.setCourseId(source.getCourseId());
        target.setDescription(source.getDescription());
        target.setVideoUrl(source.getVideoUrl());
        target.setVideoDuration(source.getVideoDuration());
        target.setTitle(source.getTitle());
    }
}
