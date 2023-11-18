package learn.web.api.controllers;

import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "true")
public class CourseController {

    private final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseFacade courseFacade;

    @PostMapping("/course")
    public ResponseEntity<CourseCreateResponseData> handleCreateCourse(@RequestBody CourseData courseData) {

        CourseCreateResponseData createdCourse = null;
        try {
            createdCourse = courseFacade.createCourse(courseData);
        } catch (Exception e) {
            LOGGER.error("Course not created", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(createdCourse);
    }
}
