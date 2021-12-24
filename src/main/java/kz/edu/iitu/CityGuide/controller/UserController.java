package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.controller.dto.request.UserLoginDto;
import kz.edu.iitu.CityGuide.controller.dto.request.UserSignupDto;
import kz.edu.iitu.CityGuide.controller.dto.response.JwtResponse;
import kz.edu.iitu.CityGuide.controller.dto.response.UserDto;
import kz.edu.iitu.CityGuide.feature.exception.RecordAlreadyExistsException;
import kz.edu.iitu.CityGuide.feature.exception.RecordNotFoundException;
import kz.edu.iitu.CityGuide.feature.security.jwt.JwtUtil;
import kz.edu.iitu.CityGuide.feature.security.service.UserDetailsImpl;
import kz.edu.iitu.CityGuide.repository.UserRepository;
import kz.edu.iitu.CityGuide.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserRepository userRepository, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " does not exist"));
        UserDto userDto = UserDto.build(user);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserSignupDto userSignupDto) {
        boolean isUsernameTaken = userRepository.existsByUsername(userSignupDto.getUsername());
        boolean isEmailTaken = userRepository.existsByEmail(userSignupDto.getEmail());
        if (isUsernameTaken && isEmailTaken) {
            throw new RecordAlreadyExistsException("Username " + userSignupDto.getUsername() + " is already taken. " +
                    "Email " + userSignupDto.getEmail() + " is already taken.");
        } else if (isUsernameTaken) {
            throw new RecordAlreadyExistsException("Username " + userSignupDto.getUsername() + " is already taken");
        } else if (isEmailTaken) {
            throw new RecordAlreadyExistsException("Email " + userSignupDto.getEmail() + " is already taken");
        }
        User user = User.builder()
                .role(User.ROLE_USER)
                .email(userSignupDto.getEmail())
                .username(userSignupDto.getUsername())
                .password("yJHlPgnRusZrqpXIVQrsugzw8LKT70mLFUa2ReIa6pcSOKBf5poxgCFRDJp1")
                .build();
        User savedUser = userRepository.save(user);
        UserDto userDto = UserDto.build(savedUser);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @GetMapping("/all")
    @PermitAll
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @RolesAllowed({User.ROLE_USER, User.ROLE_ADMIN})
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @RolesAllowed({User.ROLE_ADMIN})
    public String adminAccess() {
        return "Admin Content.";
    }
}
