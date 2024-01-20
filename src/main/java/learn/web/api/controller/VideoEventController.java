package learn.web.api.controller;

import learn.web.api.facade.VideoEventFacade;
import learn.web.api.facade.dto.CreateEventData;
import learn.web.api.facade.dto.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class VideoEventController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private VideoEventFacade videoEventFacade;
    @PostMapping("/events")
    public ResponseEntity<?> handleGetCurrentUserData(@RequestBody CreateEventData createEventData) {
        CreateEventData createdEvent;
        try {
             createdEvent = videoEventFacade.creteVideoEvent(createEventData);
        } catch (Exception e) {
            LOGGER.debug("Video Event creation failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Video Event creation failed");
        }
        return ResponseEntity.ok(createdEvent);
    }
}
