package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class ChapterData {

    private String id;
    private String title;
    private String description;
    private String videoUrl;
    private String courseId;

}