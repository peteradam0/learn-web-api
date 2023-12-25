package learn.web.api.controller;

import learn.web.api.facade.dto.CanvasAuthData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", allowedHeaders = "true")
public class CanvasLMSController {

    @PostMapping("/authorize")
    public ResponseEntity<?> handleAuthUser(@RequestBody CanvasAuthData canvasAuthData) {

        return ResponseEntity.ok("hello");
    }
}
