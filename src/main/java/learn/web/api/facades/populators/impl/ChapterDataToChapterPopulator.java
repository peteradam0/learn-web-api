package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.ChapterData;
import learn.web.api.facades.dtos.CreateChapterData;
import learn.web.api.facades.populators.Populator;
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
        target.setTitle(source.getTitle());
    }
}
