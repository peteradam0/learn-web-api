package learn.web.api.controllers;

import learn.web.api.facades.ChapterFacade;
import learn.web.api.facades.CourseFacade;
import learn.web.api.facades.dtos.*;
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

    @Autowired
    private ChapterFacade chapterFacade;

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

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseData> handleGetCourses(@PathVariable String courseId) {
        CourseData courseData = new CourseData();
        try {
            courseData = courseFacade.getCourseData(courseId);
        } catch (Exception e) {
            LOGGER.error("Get courses for user failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(courseData);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<String> handlePublication(@PathVariable String courseId, @RequestBody PublicationData publicationData) {

        try {
            courseFacade.changePublication(courseId);
        } catch (Exception e) {
            LOGGER.error("Get courses for user failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok("Change publication success");

    }

    @GetMapping("/courses/{courseId}/chapters")
    public ResponseEntity<List<ChapterData>> handleGetChapterOfCourses(@PathVariable String courseId) {
        List<ChapterData> chapterData = new ArrayList<>();
        try {
            chapterData = chapterFacade.getChapterData(courseId);
        } catch (Exception e) {
            LOGGER.error("Get chapters for user failed", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(chapterData);
    }

    @PostMapping("/courses/{courseId}/chapters")
    public ResponseEntity<ChapterData> handleCreateCourseChapters(@PathVariable String courseId, @RequestBody CreateChapterData createChapterData) {
        ChapterData chapterData = new ChapterData();
        try {
            chapterData = chapterFacade.createChapter(courseId, createChapterData);
        } catch (Exception e) {
            LOGGER.error("Create chapter error ", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(chapterData);
    }

    @DeleteMapping("/courses/{courseId}/chapters/{chapterId}")
    public ResponseEntity<String> handleDeleteCourseChapters(@PathVariable String courseId, @PathVariable String chapterId) {

        try {
            chapterFacade.deleteChapter(chapterId);
        } catch (Exception e) {
            LOGGER.error("Create chapter error ", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok("Chapter deleted");
    }

    @PutMapping("/courses/{courseId}/chapters/{chapterId}")
    public ResponseEntity<ChapterData> handlePutCourseChapters(@PathVariable String courseId, @PathVariable String chapterId, @RequestBody ChapterData chapterData) {
        try {
            chapterFacade.updateChapter(chapterData);
        } catch (Exception e) {
            LOGGER.error("Chapter update error ", e);
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(chapterData);
    }


}
