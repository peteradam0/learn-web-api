package learn.web.api.facade.impl;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.dto.DeleteVideoEventData;
import learn.web.api.facade.dto.VideoEventData;
import learn.web.api.facade.populator.impl.CreateVideoEventToVideoEventPopulator;
import learn.web.api.facade.populator.impl.VideoEventToVideoEventDataPopulator;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultVideoEventFacade implements VideoEventFacade {

    @Autowired
    private VideoEventService videoEventService;

    @Autowired
    private CreateVideoEventToVideoEventPopulator createVideoEventToVideoEventPopulator;

    @Autowired
    private VideoEventToVideoEventDataPopulator videoEventDataPopulator;

    @Override
    public CreateEventData creteVideoEvent(CreateEventData createEventData) {
        VideoEvent videoEvent = new VideoEvent();
        createVideoEventToVideoEventPopulator.populate(createEventData, videoEvent);
        videoEventService.createVideoEvent(videoEvent);
        return createEventData;
    }

    @Override
    public List<VideoEventData> getVideoEvents() {
        List<VideoEvent> videoEvents = videoEventService.getVideoEvents();

        if (videoEvents == null) {
            return new ArrayList<>();
        }

        List<VideoEventData> videoEventDataList = new ArrayList<>();
        videoEvents.forEach(videoEvent -> {
            VideoEventData videoEventData = new VideoEventData();
            videoEventDataPopulator.populate(videoEvent, videoEventData);
            videoEventDataList.add(videoEventData);
        });

        return videoEventDataList;
    }

    @Override
    public void removeVideoEvent(DeleteVideoEventData deleteVideoEventData) {
        videoEventService.removeVideoEvent(deleteVideoEventData.getName(), deleteVideoEventData.getOrganization());
    }
}
