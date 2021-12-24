package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.controller.dto.response.PlaceDto;
import kz.edu.iitu.CityGuide.repository.CommentRepository;
import kz.edu.iitu.CityGuide.repository.PlaceRepository;
import kz.edu.iitu.CityGuide.repository.entity.Comment;
import kz.edu.iitu.CityGuide.repository.entity.Place;
import kz.edu.iitu.CityGuide.repository.entity.Rating;
import kz.edu.iitu.CityGuide.repository.entity.Tag;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public List<PlaceDto> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(this::getPlaceDtoFromPlace)
                .collect(Collectors.toList());
    }

    @Transactional
    PlaceDto getPlaceDtoFromPlace(Place place) {
        List<String> tags = place.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());

        BigDecimal averageRating = null;
        if (place.getRatings().size() > 0) {
            averageRating = calculatePlaceAverageRating(place);
        }

        List<Comment> comments = commentRepository.findCommentsOfPlace(place);
        comments = comments.size() > 0 ? comments : null;

        return new PlaceDto(place.getName(), place.getDescription(),
                place.getAddress().getStreet(), place.getAddress().getBuildingNumber(),
                place.getPhoneNumber(), place.getWebsiteUrl(),
                tags, averageRating, comments);
    }

    private static BigDecimal calculatePlaceAverageRating(Place place) {
        List<Rating> ratings = place.getRatings();
        BigDecimal[] totalWithCount = ratings.stream()
                .map(Rating::getValue)
                .map(rating -> new BigDecimal[]{rating, BigDecimal.ONE})
                .reduce((a, b) -> new BigDecimal[]{a[0].add(b[0]), a[1].add(BigDecimal.ONE)})
                .orElseThrow(NoSuchElementException::new);
        return totalWithCount[0].divide(totalWithCount[1], RoundingMode.HALF_UP);
    }
}
