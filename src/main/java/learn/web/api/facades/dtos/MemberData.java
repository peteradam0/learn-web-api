package learn.web.api.facades.dtos;

import lombok.Getter;
import org.springframework.boot.jackson.JsonComponent;

@Getter
@JsonComponent
public class MemberData {

    private String userName;

    public MemberData() {

    }
    public MemberData(String userName) {
        this.userName = userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
}
