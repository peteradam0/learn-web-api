package learn.web.api.facades.dtos;

import learn.web.api.models.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class UserData {

    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private UserRole userRole;
    private String clerkId;
}
