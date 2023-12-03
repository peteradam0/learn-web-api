package learn.web.api.facades.populators.impl;

import learn.web.api.facades.dtos.CreateChapterData;
import learn.web.api.facades.populators.Populator;
import learn.web.api.models.Chapter;
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
