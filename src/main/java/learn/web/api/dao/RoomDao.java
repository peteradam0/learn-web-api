package learn.web.api.dao;

import learn.web.api.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomDao extends MongoRepository<Room, String> {
    void deleteRoomByRoomId(String roomId);

    Room findByRoomId(String roomId);
}
