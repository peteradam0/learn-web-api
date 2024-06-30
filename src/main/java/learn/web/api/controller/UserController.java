package learn.web.api.controller;

import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.UserFacade;
import learn.web.api.facade.dto.CanvasRequestData;
import learn.web.api.facade.dto.UserData;
import learn.web.api.facade.dto.UserRoleChangeData;
import learn.web.api.facade.dto.UserSuggestionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private OrganizationFacade organizationFacade;

    @GetMapping("/user")
    public ResponseEntity<?> handleGetCurrentUserData() {
        UserData userData = new UserData();
        try {
            userData = userFacade.getCurrentUserData();
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(userData);
    }

    @GetMapping("/users")
    public ResponseEntity<?> handleGetUsers() {
        List<UserData> userData = new ArrayList<>();
        try {
            userData = userFacade.getUsers();
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
        }
        return ResponseEntity.ok(userData);
    }

    @PostMapping ("/users/invitation")
    public ResponseEntity<?> handleUserInvitation(@RequestBody  UserSuggestionData userSuggestionData) {

        try {
            userFacade.sendUserInvite(userSuggestionData.getEmail());
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found");
        }
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> handleDeleteUser(@PathVariable String userId) {
        try {
            userFacade.deleteUser(userId);
        } catch (Exception e) {
            LOGGER.debug("User removal failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User deletion failed");
        }
        return ResponseEntity.ok("deleted");
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<String> handleUpdateUser(@RequestBody UserRoleChangeData userRoleChangeData, @PathVariable String userId) {
        try {
            userFacade.updateUserRole(userId, userRoleChangeData);
        } catch (Exception e) {
            LOGGER.debug("User update failed", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User update failed");
        }
        return ResponseEntity.ok("updated");
    }

    @PostMapping("/users/create/suggestions")
    public ResponseEntity<?> handleGetSuggestions(@RequestBody CanvasRequestData canvasDomainData) {
        List<UserSuggestionData> members = new ArrayList<>();

        try {
            members = organizationFacade.getUserSuggestions(canvasDomainData.getCanvasDomain());
        } catch (Exception e) {
            LOGGER.error("Get user suggestions failed", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(members);
        }
        return ResponseEntity.ok(members);
    }

}

