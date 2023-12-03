package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.ChapterData;
import learn.web.api.facades.populators.Populator;
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
