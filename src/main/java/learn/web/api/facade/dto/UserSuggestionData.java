package learn.web.api.facade.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@Getter
@Setter
public class UserSuggestionData {
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
