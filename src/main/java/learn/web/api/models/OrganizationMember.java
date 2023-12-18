package learn.web.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "OrganizationMember")
public class OrganizationMember {
    @Id
    private String id;
    private String organizationId;
    private String userId;
}
