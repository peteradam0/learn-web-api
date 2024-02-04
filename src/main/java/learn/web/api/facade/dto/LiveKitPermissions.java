package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class LiveKitPermissions {
    private boolean canSubscribe;
    private boolean canPublish;
}
