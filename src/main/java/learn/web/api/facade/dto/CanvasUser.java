package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class CanvasUser {
    private String id;
    private String name;
    private String sortable_name;
    private String short_name;
    private String sis_user_id;
    private String integration_id;
    private String sis_import_id;
    private String login_id;
}
