package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
}
