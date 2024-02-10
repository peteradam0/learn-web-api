package learn.web.api.facade.impl;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.*;
import learn.web.api.facade.populator.impl.CreateVideoEventToVideoEventPopulator;
import learn.web.api.facade.populator.impl.UserToUserDataPopulator;
import learn.web.api.facade.populator.impl.VideoEventToVideoEventDataPopulator;
import learn.web.api.model.User;
import learn.web.api.model.VideoEvent;
import learn.web.api.service.RoomService;
import learn.web.api.service.SessionService;
import learn.web.api.service.UserService;
import learn.web.api.service.VideoEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DefaultVideoEventFacade implements VideoEventFacade {

    @Autowired
    private VideoEventService videoEventService;

    @Autowired
    private CreateVideoEventToVideoEventPopulator createVideoEventToVideoEventPopulator;

    @Autowired
    private VideoEventToVideoEventDataPopulator videoEventDataPopulator;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserToUserDataPopulator userToUserDataPopulator;

    @Autowired
    private RoomService roomService;

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

    @Override
    public void startVideoEvent(StartVideoEventData startVideoEventData) {
        videoEventService.startEvent(startVideoEventData.getOrganization(), startVideoEventData.getName(), startVideoEventData.getRoomId());
    }

    @Override
    public List<VideoEventData> getEventsForCurrentUser() {
        List<VideoEvent> videoEvents = videoEventService.getVideoEventsForCurrentUser();

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
    public ParticipantData getEventUserPermission(String roomId) {
        User currentUser = userService.getUserById(sessionService.getCurrentUserId());
        VideoEvent videoEvent = videoEventService.getVideoEventByRoomId(roomId);

        if (videoEvent.getUsers().contains(currentUser) || videoEvent.getOrganizer().equals(currentUser)) {
            ParticipantData participantData = new ParticipantData();
            participantData.setUsername(currentUser.getUsername());
            participantData.setAdmin(Objects.equals(videoEvent.getOrganizer().getUsername(), currentUser.getUsername()));
            return participantData;
        }
        return new ParticipantData();
    }

    @Override
    public List<ParticipantData> getRoomParticipants(String roomId) {
        List<User> participants = roomService.getRoomById(roomId).getParticipants();
        VideoEvent videoEvent = videoEventService.getVideoEventByRoomId(roomId);

        List<ParticipantData> userDataList = new ArrayList<>();

        if (participants != null) {
            participants.forEach(participant -> {
                ParticipantData userData = new ParticipantData();
                userData.setUsername(participant.getUsername());
                userData.setAdmin(Objects.equals(videoEvent.getOrganizer().getUsername(), participant.getUsername()));
                userDataList.add(userData);
            });
        }
        return userDataList;
    }
}
