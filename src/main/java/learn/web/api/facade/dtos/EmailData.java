package learn.web.api.facade.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
@Getter
@Setter
public class EmailData {
    List<EmailAddress> email_addresses;
    String id;
    String username;
    String image_url;
    String first_name;
    String last_name;
}

