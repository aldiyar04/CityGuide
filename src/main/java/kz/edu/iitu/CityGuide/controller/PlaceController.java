package kz.edu.iitu.CityGuide.controller;

import kz.edu.iitu.CityGuide.CityGuideApplication;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(CityGuideApplication.API_URI_PATH + "/places")
@RolesAllowed(User.ROLE_ADMIN)
@AllArgsConstructor
public class PlaceController {

}
