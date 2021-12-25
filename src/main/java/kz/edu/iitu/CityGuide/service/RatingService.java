package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;


}
