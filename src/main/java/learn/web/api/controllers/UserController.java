package learn.web.api.controllers;

import learn.web.api.facades.UserFacade;
import learn.web.api.facades.dtos.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/user")
    public ResponseEntity<UserData> handleGetCurrentUserData() {
        UserData userData = new UserData();
        try {
            userData = userFacade.getCurrentUserData();
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserData>> handleGetUsers() {
        List<UserData> userData = new ArrayList<>();
        try {
            userData = userFacade.getUsers();
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(userData);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> handleDeleteUser(@PathVariable String userId) {
        try {
            userFacade.deleteUser(userId);
        } catch (Exception e) {
            LOGGER.debug("User removal failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok("deleted");
    }
}
