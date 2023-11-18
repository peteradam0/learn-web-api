package learn.web.api.controllers;

import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.dtos.CourseCreateResponseData;
import learn.web.api.facades.dtos.CourseCreateRequestData;
import learn.web.api.facades.dtos.CourseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "true")
public class CourseController {

    private final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseFacade courseFacade;

    @PostMapping("/courses")
    public ResponseEntity<CourseCreateResponseData> handleCreateCourse(@RequestBody CourseCreateRequestData courseCreateRequestData) {

        CourseCreateResponseData createdCourse = null;
        try {
            createdCourse = courseFacade.createCourse(courseCreateRequestData);
        } catch (Exception e) {
            LOGGER.error("Course not created", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(createdCourse);
    }

    @GetMapping("/administration/courses")
    public ResponseEntity<List<CourseData>> handleGetCoursesForUser() {

        List<CourseData> courseDataList = new ArrayList<>();
        try {
            courseDataList = courseFacade.getCourseDataForUser();
        } catch (Exception e) {
            LOGGER.error("Get courses for user failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(courseDataList);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseData>> handleGetCourses() {
        List<CourseData> courseDataList = new ArrayList<>();
        try {
            courseDataList = courseFacade.getCourses();
        } catch (Exception e) {
            LOGGER.error("Get courses for user failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(courseDataList);
    }


}
