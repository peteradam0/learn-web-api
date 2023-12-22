package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter
@Setter
public class OrganizationData {
    private String id;
    private String name;
    private String imageUrl;
    private List<UserData> members;
}
