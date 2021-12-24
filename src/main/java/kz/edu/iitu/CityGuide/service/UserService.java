package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.controller.dto.request.UserLoginDto;
import kz.edu.iitu.CityGuide.controller.dto.request.UserSignupDto;
import kz.edu.iitu.CityGuide.controller.dto.response.JwtDto;
import kz.edu.iitu.CityGuide.controller.dto.UserDto;
import kz.edu.iitu.CityGuide.feature.exception.RecordAlreadyExistsException;
import kz.edu.iitu.CityGuide.feature.exception.RecordNotFoundException;
import kz.edu.iitu.CityGuide.feature.security.jwt.JwtUtil;
import kz.edu.iitu.CityGuide.feature.security.service.UserDetailsImpl;
import kz.edu.iitu.CityGuide.repository.UserRepository;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::build)
                .collect(Collectors.toList());
    }

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

    public JwtDto authenticateUser(UserLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getRoles());
    }

    public UserDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " does not exist"));
        return UserDto.build(user);
    }

    public UserDto updateUser(long id, UserDto newUserDto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("User with id " + id + " does not exist");
                });

        oldUser.setRole(newUserDto.getRole());
        oldUser.setEmail(newUserDto.getEmail());
        oldUser.setUsername(newUserDto.getUsername());

        // Not simply returning updated oldUser in case if in the future User or UserDto classes will be updated,
        // e.g., by adding a new updatedOn field. Then savedUser will be different from oldUser.
        User savedUser = userRepository.save(oldUser);
        return UserDto.build(savedUser);
    }

    public void deleteUser(long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new RecordNotFoundException("User with id " + id + " does not exist");
        }
        userRepository.deleteById(id);
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
