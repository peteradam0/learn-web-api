package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.ChapterData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.models.Chapter;
import org.springframework.stereotype.Component;

@Component
public class ChapterToChapterDataPopulator implements Populator<Chapter, ChapterData> {
    @Override
    public void populate(Chapter source, ChapterData target) {
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setVideoUrl(source.getVideoUrl());
        target.setTitle(source.getTitle());
        target.setVideoDuration(source.getVideoDuration());
    }
}
