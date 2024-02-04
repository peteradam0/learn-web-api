package learn.web.api.facade;

import learn.web.api.facade.dto.LiveKitEventData;

public interface RoomFacade {
    void createRoom(LiveKitEventData roomData);
    void removeRoom(LiveKitEventData roomData);
    void addParticipant(LiveKitEventData roomData);

    void removeParticipant(LiveKitEventData roomData);
}
