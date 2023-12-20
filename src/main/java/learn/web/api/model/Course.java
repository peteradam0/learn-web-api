package learn.web.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Course")
public class Course {

    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private String imageUrl;
    private String videoUrl;
    private Float price;
    private boolean isPublished;
    private String categoryId;

    @DBRef
    private List<Attachment> attachments;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
}
