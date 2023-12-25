package learn.web.api.facade.dto;

import learn.web.api.model.CourseParticipation;
import learn.web.api.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

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
    private List<CourseParticipationData> courseParticipations;
}
