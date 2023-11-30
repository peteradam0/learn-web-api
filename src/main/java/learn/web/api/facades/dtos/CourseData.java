package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter
@Setter
public class CourseData {

    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private String category;
    private String createdAt;
    private boolean isPublished;
    private List<ChapterData> chapterData;
}
