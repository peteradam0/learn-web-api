package learn.web.api.controller;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.dto.DeleteVideoEventData;
import learn.web.api.facade.dto.StartVideoEventData;
import learn.web.api.facade.dto.VideoEventData;
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
}
