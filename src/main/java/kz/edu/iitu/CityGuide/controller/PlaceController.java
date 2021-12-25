package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import kz.edu.iitu.CityGuide.controller.dto.request.PlaceCreationDto;
import kz.edu.iitu.CityGuide.controller.dto.request.PlaceUpdateDto;
import kz.edu.iitu.CityGuide.controller.dto.response.PlaceDto;
import kz.edu.iitu.CityGuide.repository.entity.User;
import kz.edu.iitu.CityGuide.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

    @GetMapping("/tag/{tag}")
    @PermitAll
    public ResponseEntity<List<PlaceDto>> getPlacesByTag(@PathVariable("tag") String tag) {
        return ResponseEntity.ok().body(placeService.getPlacesByTag(tag));
    }

    @PostMapping
    @RolesAllowed(User.ROLE_ADMIN)
    public ResponseEntity<PlaceDto> createPlace(@Valid @RequestBody PlaceCreationDto creationDto) {
        return ResponseEntity.ok().body(placeService.createPlace(creationDto));
    }

    @PutMapping("/{id}")
    @RolesAllowed(User.ROLE_ADMIN)
    public ResponseEntity<PlaceDto> updatePlace(@PathVariable("id") long id,
                                                @Valid @RequestBody PlaceUpdateDto updateDto) {
        return ResponseEntity.ok().body(placeService.updatePlace(id, updateDto));
    }

    @DeleteMapping(value = "/{id}")
    @RolesAllowed(User.ROLE_ADMIN)
    public ResponseEntity<?> deletePlace(@PathVariable("id") long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
}
