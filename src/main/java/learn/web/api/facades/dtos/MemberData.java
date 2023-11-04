package learn.web.api.facades.dtos;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class MemberData {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
