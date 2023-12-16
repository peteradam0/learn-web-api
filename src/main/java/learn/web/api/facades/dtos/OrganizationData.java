package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class OrganizationData {
    private String id;
    private String name;
    private String imageUrl;
}
