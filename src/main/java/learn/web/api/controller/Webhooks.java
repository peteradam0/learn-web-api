package learn.web.api.controller;

import com.svix.exceptions.WebhookVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import learn.web.api.facades.UserFacade;
import learn.web.api.facades.dtos.WebhookUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class Webhooks {

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/webhooks")
    public String handleGetMembers(HttpServletRequest request) {
        return "pong";
    }

    @PostMapping("/webhooks")
    public String handlePostMembers(HttpServletRequest request, @RequestBody WebhookUserData webhookUserData) throws IOException, WebhookVerificationException {

        if(webhookUserData.getData().getEmail_addresses() != null) {
            switch (webhookUserData.getType()) {
                case "user.created":
                    userFacade.createUser(webhookUserData);
                    break;
                case "user.updated":
                    userFacade.updateUser(webhookUserData);
                    break;
                default:
            }
        }
        return "hello";
    }

}
