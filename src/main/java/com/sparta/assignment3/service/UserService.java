package com.sparta.assignment3.service;


import com.sparta.assignment3.dto.SignupRequestDto;
import com.sparta.assignment3.model.User;
import com.sparta.assignment3.model.UserRoleEnum;
import com.sparta.assignment3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
// 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

// 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        UserRoleEnum role = UserRoleEnum.USER;


        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    public User getUserDetail(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new NullPointerException("사용자가 없습니다."));
    }
}