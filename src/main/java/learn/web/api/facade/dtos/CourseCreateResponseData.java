package learn.web.api.facade.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CourseCreateResponseData {

    private String title;
    private String id;

}
