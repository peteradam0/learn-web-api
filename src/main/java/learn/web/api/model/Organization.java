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
@Document(collection = "Organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private String adminId;
    private String imageUrl;
    private List<User> members;
}
