package kz.edu.iitu.CityGuide.controller.dto.request;

import kz.edu.iitu.CityGuide.feature.validation.place.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PlaceCreationDto {
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
}
