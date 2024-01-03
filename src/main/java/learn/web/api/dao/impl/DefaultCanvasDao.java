package learn.web.api.dao.impl;

import learn.web.api.dao.CanvasDao;
import learn.web.api.facade.dto.CanvasCourse;
import learn.web.api.facade.dto.CanvasUser;
import learn.web.api.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class DefaultCanvasDao implements CanvasDao {

    private final String USERS_ROOT_URI = "http://canvas.docker/api/v1/accounts/self/users";

    private final String COURSES_ROOT_URI = "http://canvas.docker/api/v1/courses";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SessionService sessionService;


    @Override
    public List<CanvasUser> findAllCanvasUsers() {
        ResponseEntity<CanvasUser[]> users = restTemplate.exchange(USERS_ROOT_URI, HttpMethod.GET, createHeaders(), CanvasUser[].class);

        if (users.getBody() == null) {
            throw new RuntimeException("No users found in Canvas LMS");
        }

        return List.of(users.getBody());
    }

    @Override
    public List<CanvasCourse> findAllCanvasCourses() {
        ResponseEntity<CanvasCourse[]> courses = restTemplate.exchange(COURSES_ROOT_URI, HttpMethod.GET, createHeaders(), CanvasCourse[].class);

        if (courses.getBody() == null) {
            throw new RuntimeException("No courses found in Canvas LMS");
        }

        return List.of(courses.getBody());
    }

    private HttpEntity<String> createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String token = sessionService.getCanvasToken();
        headers.set("Authorization", "Bearer " + token);
        return new HttpEntity<String>(headers);
    }
}
