package kz.edu.iitu.CityGuide.controller.dto;

import kz.edu.iitu.CityGuide.feature.validation.*;
import kz.edu.iitu.CityGuide.feature.validation.place.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
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
}
