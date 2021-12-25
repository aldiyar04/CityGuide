package kz.edu.iitu.CityGuide.service;

import kz.edu.iitu.CityGuide.controller.dto.request.PlaceCreationDto;
import kz.edu.iitu.CityGuide.controller.dto.request.PlaceUpdateDto;
import kz.edu.iitu.CityGuide.controller.dto.response.PlaceDto;
import kz.edu.iitu.CityGuide.feature.exception.RecordNotFoundException;
import kz.edu.iitu.CityGuide.repository.CommentRepository;
import kz.edu.iitu.CityGuide.repository.PlaceRepository;
import kz.edu.iitu.CityGuide.repository.TagRepository;
import kz.edu.iitu.CityGuide.repository.entity.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;

    public List<PlaceDto> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return getPlaceDtosFromPlaces(places);
    }

    public List<PlaceDto> getPlacesByTag(String tag) {
        boolean exists = tagRepository.existsByName(tag);
        if (!exists) {
            throw new RecordNotFoundException("Tag " + tag + " does not exist");
        }
        List<Place> places = placeRepository.findPlacesByTag(tag);
        return getPlaceDtosFromPlaces(places);
    }

    public PlaceDto createPlace(PlaceCreationDto creationDto) {
        List<String> tagNames = creationDto.getTags();

        Place place = Place.builder()
                .name(creationDto.getName())
                .description(creationDto.getDescription())
                .address(new Address(creationDto.getStreet(), creationDto.getBuildingNumber()))
                .phoneNumber(creationDto.getPhoneNumber())
                .websiteUrl(creationDto.getWebsiteUrl())
                .tags(getTagsFromTagNames(tagNames))
                .build();
        Place savedPlace = placeRepository.save(place);
        return getPlaceDtoFromPlace(savedPlace);
    }

    public PlaceDto updatePlace(long id, PlaceUpdateDto updateDto) {
        Place oldPlace = getByIdOrThrowNotFoundException(id);

        String newName = updateDto.getName();
        String newDescription = updateDto.getDescription();
        String newStreet = updateDto.getStreet();
        String newBuildingNumber = updateDto.getBuildingNumber();
        String newPhoneNumber = updateDto.getPhoneNumber();
        String newWebsiteUrl = updateDto.getWebsiteUrl();
        List<String> newTagNames = updateDto.getTags();

        if (StringUtils.hasText(newName)) {
            oldPlace.setName(newName);
        }
        if (StringUtils.hasText(newDescription)) {
            oldPlace.setDescription(newDescription);
        }
        if (StringUtils.hasText(newStreet)) {
            oldPlace.getAddress().setStreet(newStreet);
        }
        if (StringUtils.hasText(newBuildingNumber)) {
            oldPlace.getAddress().setBuildingNumber(newBuildingNumber);
        }
        if (StringUtils.hasText(newPhoneNumber)) {
            oldPlace.setPhoneNumber(newPhoneNumber);
        }
        if (StringUtils.hasText(newWebsiteUrl)) {
            oldPlace.setWebsiteUrl(newWebsiteUrl);
        }
        if (!CollectionUtils.isEmpty(newTagNames) && !newTagNames.contains(null)) {
            List<Tag> tags = getTagsFromTagNames(newTagNames);
            oldPlace.setTags(tags);
        }

        Place savedPlace = placeRepository.save(oldPlace);
        return getPlaceDtoFromPlace(savedPlace);
    }

    public void deletePlace(long id) {
        getByIdOrThrowNotFoundException(id);
        placeRepository.deleteById(id);
    }

    private List<PlaceDto> getPlaceDtosFromPlaces(List<Place> places) {
        return places.stream()
                .map(this::getPlaceDtoFromPlace)
                .collect(Collectors.toList());
    }

    private PlaceDto getPlaceDtoFromPlace(Place place) {
        List<String> tags = place.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());

        BigDecimal averageRating = null;
        if (place.getRatings() != null && !place.getRatings().isEmpty()) {
            averageRating = calculatePlaceAverageRating(place);
        }

        List<Comment> comments = commentRepository.findCommentsOfPlace(place);
        comments = comments == null || comments.isEmpty() ? null : comments;

        return new PlaceDto(place.getName(), place.getDescription(),
                place.getAddress().getStreet(), place.getAddress().getBuildingNumber(),
                place.getPhoneNumber(), place.getWebsiteUrl(),
                tags, place.getId(), averageRating, comments);
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

    private List<Tag> getTagsFromTagNames(List<String> tagNames) {
        StringBuilder tagNotFoundMessages = new StringBuilder();

        List<Tag> tags = tagNames.stream()
                .map(tagName -> {
                    Optional<Tag> tagOptional = tagRepository.findTagByName(tagName);
                    if (!tagOptional.isPresent()) {
                        tagNotFoundMessages.append(String.format("Tag %s does not exist. ", tagName));
                    }
                    return tagOptional;
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if (tagNotFoundMessages.length() > 0) {
            throw new RecordNotFoundException(tagNotFoundMessages.toString());
        }
        return tags;
    }

    private Place getByIdOrThrowNotFoundException(long id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RecordNotFoundException("Place with id " + id + " does not exist");
                });
    }
}
