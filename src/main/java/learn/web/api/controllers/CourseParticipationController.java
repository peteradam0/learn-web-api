package learn.web.api.controllers;

import learn.web.api.facades.CourseParticipationFacade;
import learn.web.api.facades.dtos.CourseParticipationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/participation")
    public ResponseEntity<CourseParticipationData> handleGetParticipation(@PathVariable String courseId) {
        CourseParticipationData courseParticipationData = new CourseParticipationData();
        try {
            courseParticipationData = courseParticipationFacade.getParticipation(courseId);
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(courseParticipationData);
    }

    @PostMapping("/chapter/{chapterId}/participation")
    public ResponseEntity<String> handleCreateChapterParticipation(@PathVariable String courseId, @PathVariable String chapterId) {
        try {
            courseParticipationFacade.createChapterParticipation(courseId, chapterId);
        } catch (Exception e) {
            LOGGER.debug("Participation not found", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok("Successfull participation");
    }
}
