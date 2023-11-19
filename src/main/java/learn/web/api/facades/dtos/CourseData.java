package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CourseData {

    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private boolean isPublished;
}
