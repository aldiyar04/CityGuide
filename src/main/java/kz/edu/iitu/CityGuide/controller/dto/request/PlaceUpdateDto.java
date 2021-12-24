package kz.edu.iitu.CityGuide.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class PlaceUpdateDto {
    @Size(min = 3, max = 64, message = "Place name must be 3-64 characters long")
    private final String name;

    @Size(min = 64, max = 4096, message = "Place description must be 64-4096 characters long")
    private final String description;

    @Size(min = 3, max = 64, message = "Street name must be 3-64 characters long")
    private final String street;

    @Size(min = 1, max = 4, message = "Building number must be 1-4 characters long")
    private final String buildingNumber;

    @Size(min = 14, max = 14, message = "Phone number must be exactly 14 characters long. Format: 7-707-123-4567.")
    private final String phoneNumber;

    @Size(min = 3, max = 2048, message = "Website URL must be 3-2048 characters long")
    private final String websiteUrl;

    @Size(min = 1, max = 16, message = "Place must have 1-16 tags")
    private final List<String> tags;
}
