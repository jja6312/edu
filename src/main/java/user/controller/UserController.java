package user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.dto.*;
import user.entity.User;
import user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    // CRUD

    // Create
    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest){
        User user = userService.createUser(userCreateRequest);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    // Read_v1 (예시: localhost:8080/users/1)
    @GetMapping("/{userId}")
    public UserResponse getUserV1(@PathVariable Long userId){ // uri에 전달된 userId로
        User user = userService.getUser(userId); //비즈니스 로직을 통해 User를 꺼내온다.

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    // Read_v2 (예시: localhost:8080/users?userId=1)
    @GetMapping
    public UserResponse getUserV2(@RequestParam Long userId){ // 수업에서 배운 request.getParameter와 같음. 다만 Long으로 형변환 자동지원!
        User user = userService.getUser(userId); // userId를 키로, user를 찾아온다.

        return UserResponse.builder() // UserResponse 빌더로 생성
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();//빌드로 객체 생성 마무리
    }

    // Read_v3 : ModelAttribute 라는게 있습니다. RequestBody처럼 간편하게 dto와 엮을 수 있는 GetMapping 방법 중 하나.



    // Update
    @PostMapping("/update")
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest){ // post요청이므로 @RequestBody로 json을 객체로 파싱(역직렬화 라고 부릅니다.)
        User user = userService.updateUser(userUpdateRequest); // 업데이트Request DTO로, user를 업데이트 완료하고 해당 유저 정보를 가져온다.

        return UserResponse.builder() // 빌더를 통해 객체 생성해서 반환
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }


    // Delete
    @PostMapping("/delete")
    public UserDeleteResponse deleteUser(@RequestBody UserDeleteRequest userDeleteRequest){
        Long userId = userService.deleteUser(userDeleteRequest); // dto를 들고 가서 해당 user 삭제, 응답반환을 위해 userId를 반환받음

        return UserDeleteResponse.builder() // 삭제 객체 생성
                .message("userId:"+userId+", 삭제 성공")
                .build();
    }


}
