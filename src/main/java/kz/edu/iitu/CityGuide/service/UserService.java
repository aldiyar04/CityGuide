package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.controller.dto.request.UserSignupDto;
import kz.edu.iitu.CityGuide.controller.dto.response.UserDto;
import kz.edu.iitu.CityGuide.feature.exception.RecordAlreadyExistsException;
import kz.edu.iitu.CityGuide.feature.exception.RecordNotFoundException;
import kz.edu.iitu.CityGuide.feature.security.jwt.JwtUtil;
import kz.edu.iitu.CityGuide.repository.UserRepository;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDto registerUser(UserSignupDto signupDto) {
        checkIfUserAlreadyExists(signupDto);

        User user = User.builder()
                .role(User.ROLE_USER)
                .email(signupDto.getEmail())
                .username(signupDto.getUsername())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .build();
        User savedUser = userRepository.save(user);
        return UserDto.build(savedUser);
    }

    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " does not exist"));
        return UserDto.build(user);
    }

    private void checkIfUserAlreadyExists(UserSignupDto signupDto) {
        boolean isUsernameTaken = userRepository.existsByUsername(signupDto.getUsername());
        boolean isEmailTaken = userRepository.existsByEmail(signupDto.getEmail());

        if (isUsernameTaken && isEmailTaken) {
            throw new RecordAlreadyExistsException(String.format("Email %s and username %s are already taken",
                    signupDto.getEmail(), signupDto.getUsername()));
        } else if (isUsernameTaken) {
            throw new RecordAlreadyExistsException("Username " + signupDto.getUsername() + " is already taken");
        } else if (isEmailTaken) {
            throw new RecordAlreadyExistsException("Email " + signupDto.getEmail() + " is already taken");
        }
    }
}
