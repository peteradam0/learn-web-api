package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class UserData {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
