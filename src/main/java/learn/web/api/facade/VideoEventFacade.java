package learn.web.api.facade;

import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.dto.VideoEventData;

import java.util.List;

public interface VideoEventFacade {
    CreateEventData creteVideoEvent(CreateEventData createEventData);

    List<VideoEventData> getVideoEvents();
}
