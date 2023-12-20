package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CreateChapterData {

    private String title;
    private String description;
    private String videoUrl;
    private String videoDuration;

}
