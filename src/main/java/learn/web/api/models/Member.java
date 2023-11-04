package learn.web.api.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Member extends BaseEntity{
    private String userName;
}
