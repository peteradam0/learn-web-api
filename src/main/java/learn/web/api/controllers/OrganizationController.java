package learn.web.api.controllers;

import learn.web.api.facades.OrganizationFacade;
import learn.web.api.facades.dtos.OrganizationData;
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

    @PostMapping("/organizations")
    public ResponseEntity<OrganizationData> handleCreateOrganization(@RequestBody OrganizationData organizationData) {

        OrganizationData createdOrganization = new OrganizationData();
        try {
            createdOrganization = organizationFacade.createOrganization(organizationData);
        } catch (Exception e) {
            LOGGER.error("Organization not created", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(createdOrganization);
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
}
