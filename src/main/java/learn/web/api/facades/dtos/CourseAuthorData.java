package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CourseAuthorData {
    private String firstName;
    private String lastName;
    private String imageUrl;
}
