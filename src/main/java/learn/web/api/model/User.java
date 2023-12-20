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
@Document(collection = "User")
public class User {

    @Id
    private String id;
    private String clerkId;
    private String email;
    private String username;
    private List<CourseParticipation> courseParticipations;
    private String imageUrl;
    private String firstname;
    private String lastname;
    private UserRole userRole;
}
