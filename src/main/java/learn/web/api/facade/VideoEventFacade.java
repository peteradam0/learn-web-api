package learn.web.api.facade;

import learn.web.api.facade.dto.*;

import javax.mail.Part;
import java.util.List;

public interface VideoEventFacade {
    CreateEventData creteVideoEvent(CreateEventData createEventData);

    List<VideoEventData> getVideoEvents();

   void removeVideoEvent(DeleteVideoEventData deleteVideoEventData);

    void startVideoEvent(StartVideoEventData startVideoEventData);

    List<VideoEventData> getEventsForCurrentUser();

    ParticipantData getEventUserPermission(String roomId);

    List<ParticipantData> getRoomParticipants(String roomId);

    ParticipantData getParticipantByUsername(String username);
}
