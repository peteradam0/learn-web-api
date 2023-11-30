package learn.web.api.controllers;

import com.svix.exceptions.WebhookVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import learn.web.api.facades.UserFacade;
import learn.web.api.facades.dtos.UserData;
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
    public String handlePostMembers(HttpServletRequest request, @RequestBody UserData userData) throws IOException, WebhookVerificationException {

        if (userData.getType().equalsIgnoreCase("user.created") && userData.getData().getEmail_addresses() != null) {
            userFacade.createUser(userData);
        }

        return "hello";
    }
}
