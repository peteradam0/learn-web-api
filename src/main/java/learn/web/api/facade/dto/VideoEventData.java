package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter
@Setter
public class VideoEventData {

    private String name;
    private String description;
    private String imageUrl;
    private String organization;
    private List<UserData> users;
    private boolean active;
    private UserData organizer;
    private String roomId;
    private String date;

}
