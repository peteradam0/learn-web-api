package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class LiveKitParticipant {
    private String sid;
    private String identity;
    private String state;
    private LiveKitPermissions permission;
}
