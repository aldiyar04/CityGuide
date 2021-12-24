package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import kz.edu.iitu.CityGuide.controller.dto.response.PlaceDto;
import kz.edu.iitu.CityGuide.repository.entity.User;
import kz.edu.iitu.CityGuide.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(CityGuideApplication.API_URI_PATH + "/places")
@RolesAllowed(User.ROLE_ADMIN)
@AllArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    @PermitAll
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }
}
