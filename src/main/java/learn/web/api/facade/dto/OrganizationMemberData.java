package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class OrganizationMemberData {
    private String id;
    private String userEmail;
    private String organizationName;
    private boolean confirmed;
}
