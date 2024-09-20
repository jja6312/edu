package user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.dto.UserCreateRequest;
import user.dto.UserDeleteRequest;
import user.dto.UserUpdateRequest;
import user.entity.User;
import user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    // Create
    public User createUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .name(userCreateRequest.getName())
                .email(userCreateRequest.getEmail())
                .build();

        return userRepository.save(user);
    }

    // Read - v1,v2
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("해당하는 유저 아이디가 없습니다"));
    }

    // Update
    public User updateUser(UserUpdateRequest userUpdateRequest) {
        return userRepository.save(User.builder()
                        .id(userUpdateRequest.getUserId())
                        .name(userUpdateRequest.getName())
                        .email(userUpdateRequest.getEmail())
                .build());
    }

    // Delete
    public Long deleteUser(UserDeleteRequest userDeleteRequest) {
        Long userId = userDeleteRequest.getUserId();
        if(userRepository.existsById(userId)){//jpa 쿼리메서드인 existsById를 통해 유저 아이디가 있는지 확인하고
            // 존재하는 아이디라면 삭제
            userRepository.deleteById(userId);
        }else{
            throw new IllegalArgumentException("해당하는 유저 아이디가 없습니다.");
        }

        return userId;
    }
}
