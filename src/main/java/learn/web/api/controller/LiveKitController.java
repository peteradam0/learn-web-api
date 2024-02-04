package learn.web.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import learn.web.api.facade.RoomFacade;
import learn.web.api.facade.dto.LiveKitEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class LiveKitController {

    @Autowired
    private RoomFacade roomFacade;

    @GetMapping("/liveweb")
    public String handleTestRequest(HttpServletRequest request) {
        return "pong";
    }

    @PostMapping("/liveweb")
    public String handleRequests(HttpServletRequest request, @RequestBody LiveKitEventData liveKitEventData) {
        if (!Objects.equals(liveKitEventData.getEvent(), "")) {
            switch (liveKitEventData.getEvent()) {
                case "room_started":
                    roomFacade.createRoom(liveKitEventData);
                    break;
                case "room_finished":
                    roomFacade.removeRoom(liveKitEventData);
                    break;
                case "participant_joined":
                    roomFacade.addParticipant(liveKitEventData);
                    break;
                case "participant_left":
                    roomFacade.removeParticipant(liveKitEventData);
                    break;
                default:
            }
        }
        return "Event cannot be processed";
    }


}
