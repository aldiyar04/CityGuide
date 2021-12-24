package kz.edu.iitu.CityGuide.controller.dto.response;

import kz.edu.iitu.CityGuide.feature.validation.CheckRatingValue;
import kz.edu.iitu.CityGuide.feature.validation.place.*;
import kz.edu.iitu.CityGuide.repository.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class PlaceDto {
    @CheckPlaceName
    private final String name;

    @CheckPlaceDescription
    private final String description;

    @CheckStreetName
    private final String street;

    @CheckBuildingNumber
    private final String buildingNumber;

    @CheckPhoneNumber
    private final String phoneNumber;

    @CheckWebsiteUrl
    private final String websiteUrl;

    @CheckPlaceTags
    private final List<String> tags;

    @CheckRatingValue
    private final BigDecimal averageRating;

    private final List<Comment> comments;
}
