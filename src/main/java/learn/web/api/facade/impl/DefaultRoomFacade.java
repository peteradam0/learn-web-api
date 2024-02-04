package learn.web.api.facade.impl;

import learn.web.api.facade.RoomFacade;
import learn.web.api.facade.dto.LiveKitEventData;
import learn.web.api.facade.populator.impl.LiveKitEventDataToRoomPopulator;
import learn.web.api.model.Room;
import learn.web.api.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoomFacade implements RoomFacade {

    @Autowired
    private LiveKitEventDataToRoomPopulator liveKitEventDataToRoomPopulator;

    @Autowired
    private RoomService roomService;

    @Override
    public void createRoom(LiveKitEventData roomData) {
        Room room = new Room();
        liveKitEventDataToRoomPopulator.populate(roomData, room);
        roomService.createRoom(room);
    }

    @Override
    public void removeRoom(LiveKitEventData roomData) {
        roomService.removeRoom(roomData.getRoom().getId());
    }

    @Override
    public void addParticipant(LiveKitEventData roomData) {
        if (roomData.getParticipant() != null && roomData.getParticipant().getIdentity() != null)
            roomService.addParticipant(roomData.getParticipant().getIdentity(), roomData.getRoom().getName());
    }

    @Override
    public void removeParticipant(LiveKitEventData roomData) {
        if (roomData != null & roomData.getParticipant() != null)
            roomService.removeParticipant(roomData.getParticipant().getIdentity(), roomData.getRoom().getName());
    }


}
