package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class OrganizationMemberInvData {
    private String id;
    private String organizationId;
    private String userId;
    private boolean confirmed;
}
