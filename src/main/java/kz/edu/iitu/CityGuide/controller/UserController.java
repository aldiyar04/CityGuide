package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import kz.edu.iitu.CityGuide.controller.dto.request.UserLoginDto;
import kz.edu.iitu.CityGuide.controller.dto.request.UserSignupDto;
import kz.edu.iitu.CityGuide.controller.dto.request.UserUpdateDto;
import kz.edu.iitu.CityGuide.controller.dto.response.JwtDto;
import kz.edu.iitu.CityGuide.controller.dto.response.UserDto;
import kz.edu.iitu.CityGuide.repository.entity.User;
import kz.edu.iitu.CityGuide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(CityGuideApplication.API_URI_PATH + "/users")
@RolesAllowed(User.ROLE_ADMIN)
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PermitAll
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping
    @PermitAll
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserSignupDto signupDto) {
        UserDto userDto = userService.registerUser(signupDto);
        return ResponseEntity.ok().body(userDto);
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody UserLoginDto loginDto) {
        return ResponseEntity.ok().body(userService.authenticateUser(loginDto));
    }

    @GetMapping(value = "/{id}")
    @PermitAll
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping(value = "/{id}")
    @RolesAllowed(User.ROLE_ADMIN)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id,
                                              @Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserDto updatedUserDto = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok().body(updatedUserDto);
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed(User.ROLE_ADMIN)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
