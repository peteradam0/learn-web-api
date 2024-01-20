package learn.web.api.facade.impl;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.populator.impl.CreateVideoEventToVideoEventPopulator;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultVideoEventFacade implements VideoEventFacade {

    @Autowired
    private VideoEventService videoEventService;

    @Autowired
    private CreateVideoEventToVideoEventPopulator createVideoEventToVideoEventPopulator;
    @Override
    public CreateEventData creteVideoEvent(CreateEventData createEventData) {
        VideoEvent videoEvent = new VideoEvent();
        createVideoEventToVideoEventPopulator.populate(createEventData, videoEvent);
        videoEventService.createVideoEvent(videoEvent);
        return createEventData;
    }
}
