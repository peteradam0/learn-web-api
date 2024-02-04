package learn.web.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import learn.web.api.facade.UserFacade;
import learn.web.api.facade.dto.ClerkEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClerkUserController {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/webhooks")
    public String handleTestRequest(HttpServletRequest request) {
        return "pong";
    }

    @PostMapping("/webhooks")
    public String handlePostMembers(HttpServletRequest request, @RequestBody ClerkEventData clerkEventData) {

        if(clerkEventData.getData().getEmail_addresses() != null) {
            switch (clerkEventData.getType()) {
                case "user.created":
                    userFacade.createUser(clerkEventData);
                    break;
                case "user.updated":
                    userFacade.updateUser(clerkEventData);
                    break;
                default:
            }
        }
        return "hello";
    }

}
