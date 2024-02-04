package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class LiveKitRoom {
    private String id;
    private String sid;
    private String name;
    private int numParticipants;
    private int numPublishers;
}
