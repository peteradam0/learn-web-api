package learn.web.api.controller;

import learn.web.api.facade.EmailFacade;
import learn.web.api.facade.OrganizationFacade;
import learn.web.api.facade.dto.*;
import learn.web.api.service.SessionService;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "true")
public class OrganizationController {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationFacade organizationFacade;

    @Autowired
    private EmailFacade emailFacade;

    @PostMapping("/organizations")
    public ResponseEntity<?> handleCreateOrganization(@RequestBody OrganizationData organizationData) {

        OrganizationData createdOrganization = new OrganizationData();
        try {
            createdOrganization = organizationFacade.createOrganization(organizationData);
        } catch (Exception e) {
            LOGGER.error("Organization not created", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Creation failed");
        }
        return ResponseEntity.ok(createdOrganization);
    }

    @PostMapping("/organizations/invite")
    public ResponseEntity<?> handleAddOrganizationMember(@RequestBody OrganizationMemberData organizationMemberData) {
        try {
            emailFacade.sendOrganizationMemberInvitation(organizationMemberData);
            return ResponseEntity.ok("sent");
        } catch (Exception e) {
            LOGGER.error("Organization member not added", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Organization member not added");
        }
    }

    @PostMapping("/organizations/invite/{id}/confirmation")
    public ResponseEntity<?> handleConfirmInvitation(@PathVariable String id) {
        try {
            organizationFacade.confirmInvitation(id);
            return ResponseEntity.ok("Confirmed");
        } catch (Exception e) {
            LOGGER.error("Organization member not added", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Organization member not added");
        }
    }

    @DeleteMapping("/organizations/{name}")
    public ResponseEntity<OrganizationData> handleDeleteOrganization(@PathVariable String name) {

        OrganizationData deletedOrganization = new OrganizationData();
        try {
            organizationFacade.deleteOrganization(name);
        } catch (Exception e) {
            LOGGER.error("Organization not deleted", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(deletedOrganization);
        }
        return ResponseEntity.ok(deletedOrganization);
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationData>> handleGetOrganization() {

        List<OrganizationData> organizationDataList = new ArrayList<>();
        try {
            organizationDataList = organizationFacade.getOrganizations();
        } catch (Exception e) {
            LOGGER.error("Organizations not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(organizationDataList);
        }
        return ResponseEntity.ok(organizationDataList);
    }

    @PostMapping("/organizations/members")
    public ResponseEntity<String> handleCreateOrganizationMember(@RequestBody OrganizationMemberData organizationMemberData) {
        try {
            organizationFacade.addMemberToOrganization(organizationMemberData);
        } catch (Exception e) {
            LOGGER.error("Organization not created", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Organization member creation failed");
        }
        return ResponseEntity.ok("created");
    }

    @GetMapping("/organizations/{name}/members")
    public ResponseEntity<?> handleGetOrganizationMembers(@PathVariable String name) {
        try {
            List<UserData> members = organizationFacade.getOrganizationByName(name).getMembers();
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            LOGGER.error("Organizations members not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }

    }

    @PostMapping("/organizations/{name}/members/create/suggestions")
    public ResponseEntity<?> handleGetOrganizationMembersSuggestions(@PathVariable final String name, @RequestBody CanvasRequestData canvasRequestData) {
        try {
            List<UserSuggestionData> members = organizationFacade.getUserSuggestionsForOrganization(name, canvasRequestData.getCanvasDomain());
            return ResponseEntity.ok(members);
        } catch (Exception e) {
            LOGGER.error("Organizations members not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("/organizations/exclude/{name}")
    public ResponseEntity<?> getOrganizationsExclude(@PathVariable String name) {
        try {
            List<OrganizationData> organizationData = organizationFacade.getOrganizationsExclude(name);
            return ResponseEntity.ok(organizationData);
        } catch (Exception e) {
            LOGGER.error("Organizations not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }
}
