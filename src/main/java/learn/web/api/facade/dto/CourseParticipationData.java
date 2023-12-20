package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter
@Setter
public class CourseParticipationData {

    private String id;
    private String courseId;
    private String userId;
    private List<String> completedChapterIds;
}
