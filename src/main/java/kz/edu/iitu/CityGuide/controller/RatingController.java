package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import kz.edu.iitu.CityGuide.controller.dto.response.UserDto;
import kz.edu.iitu.CityGuide.repository.entity.User;
import kz.edu.iitu.CityGuide.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(CityGuideApplication.API_URI_PATH + "/places")
@RolesAllowed({User.ROLE_USER, User.ROLE_ADMIN})
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/{place_id}/rating")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return null;
    }
}
