package learn.web.api.facade.populator.impl;

import learn.web.api.facade.dtos.CreateChapterData;
import learn.web.api.facade.populator.Populator;
import learn.web.api.model.Chapter;
import org.springframework.stereotype.Component;

@Component
public class CreateChapterDataToChapterPopulator implements Populator<CreateChapterData, Chapter> {
    @Override
    public void populate(CreateChapterData source, Chapter target) {
        target.setDescription(source.getDescription());
        target.setVideoUrl(source.getVideoUrl());
        target.setTitle(source.getTitle());
        target.setVideoDuration(source.getVideoDuration());
    }
}
