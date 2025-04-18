package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CourseCreateRequestData {
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private String videoUrl;
    private String organization;
}
