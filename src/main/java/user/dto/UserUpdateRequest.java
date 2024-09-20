package user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserUpdateRequest {
    @NotNull(message = "유저 업데이트시 유저 아이디는 필수입니다.")
    private Long userId;

    private String name;
    private String email;
}
