package learn.web.api.controller;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VideoEventController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private VideoEventFacade videoEventFacade;

    @PostMapping("/events")
    public ResponseEntity<?> handleCreateVideoEvent(@RequestBody CreateEventData createEventData) {
        CreateEventData createdEvent;
        try {
            createdEvent = videoEventFacade.creteVideoEvent(createEventData);
        } catch (Exception e) {
            LOGGER.debug("Video Event creation failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Video Event creation failed");
        }
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/events")
    public ResponseEntity<?> handleGetVideoEventData() {
        List<VideoEventData> videoEventData;
        try {
            videoEventData = videoEventFacade.getVideoEvents();
        } catch (Exception e) {
            LOGGER.debug("Get video events failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Get video events failed");
        }
        return ResponseEntity.ok(videoEventData);
    }

    @PostMapping ("/events/delete")
    public ResponseEntity<?> handleDeleteVideoEvent(@RequestBody DeleteVideoEventData deleteVideoEventData) {

        try {
            videoEventFacade.removeVideoEvent(deleteVideoEventData);
        } catch (Exception e) {
            LOGGER.debug("Removal failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Removal failed");
        }
        return ResponseEntity.ok("Video Event removed successfully ");
    }

    @PostMapping ("/events/start")
    public ResponseEntity<?> handleStartVideoEvent(@RequestBody StartVideoEventData startVideoEventData) {

        try {
            videoEventFacade.startVideoEvent(startVideoEventData);
        } catch (Exception e) {
            LOGGER.debug("Removal failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start of video event failed");
        }
        return ResponseEntity.ok("Start successful");
    }

    @GetMapping ("/events/active")
    public ResponseEntity<?> handleGetStartedEvents() {
        List<VideoEventData> videoEventData;
        try {
            videoEventData =  videoEventFacade.getEventsForCurrentUser();
        } catch (Exception e) {
            LOGGER.debug("Get Failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No events found");
        }
        return ResponseEntity.ok(videoEventData);
    }

    @GetMapping ("/events/{roomId}")
    public ResponseEntity<?> handleGetUserPermission(@PathVariable String roomId) {
        ParticipantData participantData;
        try {
            participantData =  videoEventFacade.getEventUserPermission(roomId);
        } catch (Exception e) {
            LOGGER.debug("Get Permissions failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No events found");
        }
        return ResponseEntity.ok(participantData);
    }

    @GetMapping ("/events/room/{roomId}")
    public ResponseEntity<?> handleGetRoomParticipants(@PathVariable String roomId) {
        List<ParticipantData> userData;
        try {
            userData =  videoEventFacade.getRoomParticipants(roomId);
        } catch (Exception e) {
            LOGGER.debug("Get Participants", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No participants found");
        }
        return ResponseEntity.ok(userData);
    }

    @GetMapping ("/events/participants/{username}")
    public ResponseEntity<?> handleGetRoomParticipant(@PathVariable String username) {
        ParticipantData userData;
        try {
            userData =  videoEventFacade.getParticipantByUsername(username);
        } catch (Exception e) {
            LOGGER.debug("Get Participant", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No participant found");
        }
        return ResponseEntity.ok(userData);
    }
}
