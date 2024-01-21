package learn.web.api.facade;

import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.dto.DeleteVideoEventData;
import learn.web.api.facade.dto.StartVideoEventData;
import learn.web.api.facade.dto.VideoEventData;

import java.util.List;

public interface VideoEventFacade {
    CreateEventData creteVideoEvent(CreateEventData createEventData);

    List<VideoEventData> getVideoEvents();

   void removeVideoEvent(DeleteVideoEventData deleteVideoEventData);

    void startVideoEvent(StartVideoEventData startVideoEventData);
}
