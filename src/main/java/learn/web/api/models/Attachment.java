package learn.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Attachment")
public class Attachment extends AuditData{

    @Id
    private String id;
    private String name;
    private String url;
    private Course course;
}
