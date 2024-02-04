package learn.web.api.service.impl;

import learn.web.api.dao.RoomDao;
import learn.web.api.model.Room;
import learn.web.api.model.User;
import learn.web.api.service.RoomService;
import learn.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultRoomService implements RoomService {

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private UserService userService;

    @Override
    public void createRoom(Room newRoom) {
        Room room = roomDao.findByRoomId(newRoom.getRoomId());

        if (room == null) {
            roomDao.save(newRoom);
        }

    }

    @Override
    public void removeRoom(String roomId) {
        roomDao.deleteRoomByRoomId(roomId);
    }

    @Override
    public void addParticipant(String username, String roomId) {
        User user = userService.getUserByUsername(username);
        Room room = roomDao.findByRoomId(roomId);

        if (room == null || (room.getParticipants() != null && room.getParticipants().contains(user))) return;

        Room newRoom = room;
        roomDao.delete(room);
        List<User> userList = newRoom.getParticipants();

        if (userList == null) userList = new ArrayList<>();

        userList.add(user);
        newRoom.setParticipants(userList);
        roomDao.save(newRoom);
    }


    @Override
    public void removeParticipant(String username, String roomId) {
        User user = userService.getUserByUsername(username);
        Room room = roomDao.findByRoomId(roomId);

        if (room == null) return;

        if (room.getParticipants() == null) return;

        if (!room.getParticipants().contains(user)) return;

        Room newRoom = room;
        roomDao.delete(room);
        List<User> userList = newRoom.getParticipants();
        userList.remove(user);
        newRoom.setParticipants(userList);
        roomDao.save(newRoom);

    }

}
