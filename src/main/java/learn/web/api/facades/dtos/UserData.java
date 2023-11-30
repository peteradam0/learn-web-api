package learn.web.api.facades.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class UserData {
    EmailData data;
    String type;

    @Override
    public String toString() {
        return "UserData{" +
                "data=" + data +
                '}';
    }
}

