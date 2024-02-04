package learn.web.api.service;

import learn.web.api.model.Room;

public interface RoomService {
    void createRoom(Room room);

    void removeRoom(String id);

    void addParticipant(String username, String roomId);

    void removeParticipant(String username, String roomId);
}

