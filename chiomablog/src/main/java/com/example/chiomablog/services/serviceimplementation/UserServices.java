package com.example.chiomablog.services.serviceimplementation;

import com.example.chiomablog.entities.User;
import com.example.chiomablog.enums.Role;
import com.example.chiomablog.exceptions.UserExistsException;
import com.example.chiomablog.exceptions.checkDetailsException;
import com.example.chiomablog.exceptions.inValidPasswordException;
import com.example.chiomablog.exceptions.roleNotAllowedException;
import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.UserRequestDto;
import com.example.chiomablog.payload.UserResponseDto;
import com.example.chiomablog.repositories.UserRepository;
import com.example.chiomablog.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServices implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserResponseDto> signup(UserRequestDto userRequestDto) {
//        User newUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (userRepository.checkIfEmailExists(userRequestDto.getEmail())) {
            throw new UserExistsException("User already exists");
        }
        User newUser = User.builder()
                .name(userRequestDto.getName())
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .role(userRequestDto.getRole())
                .password(userRequestDto.getPassword())
                .build();


        if (userRepository.existsByRole(Role.ADMIN) && newUser.getRole().equals(Role.ADMIN)) {
            throw new roleNotAllowedException("Role isn't allowed, register as Customer, or Visitor");
        } else

            userRepository.save(newUser);
            UserResponseDto userResponseDto = new UserResponseDto(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getRole());
//        return new ApiResponse("User signup successful",userResponseDto,HttpStatus.CREATED);

            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        }


    @Override
    public ApiResponse login(UserRequestDto userRequestDto) {
        User user = userRepository.findByEmailAndPassword(userRequestDto.getEmail(),userRequestDto.getPassword()).orElseThrow(() -> new checkDetailsException("check email and password"));
        if (user.getPassword().equals(userRequestDto.getPassword()) && user.getEmail().equals(userRequestDto.getEmail())){
            return new ApiResponse("Login successful", HttpStatus.OK);
        }else
            throw new inValidPasswordException("password not valid");
    }
}
