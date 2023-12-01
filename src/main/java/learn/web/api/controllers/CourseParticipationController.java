package learn.web.api.controllers;

import learn.web.api.facades.CourseParticipationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/courses/{courseId}/")
public class CourseParticipationController {

    private final Logger LOGGER = LoggerFactory.getLogger(CourseParticipationController.class);

    @Autowired
    private CourseParticipationFacade courseParticipationFacade;
    @PostMapping("/participation")
    public ResponseEntity<String> handleCourseParticipation(@PathVariable String courseId) {
        try {
            courseParticipationFacade.createParticipation(courseId);
        } catch (Exception e) {
            LOGGER.error("Course not created", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok("Successfully created");
    }
}
