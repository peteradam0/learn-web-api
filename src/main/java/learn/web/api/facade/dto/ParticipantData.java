package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class ParticipantData {
    private String username;
    private boolean admin;
    private String imageUrl;
    private String email;
}
