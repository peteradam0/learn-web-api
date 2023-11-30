package learn.web.api.facades.dtos;

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
}

