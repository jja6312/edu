package user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String name;
    private String email;
}
