package learn.web.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CourseParticipation")
public class CourseParticipation {

    @Id
    private String id;
    private String userId;
    private String courseId;
    private List<String> completedChapterIds;
}
