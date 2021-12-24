package kz.edu.iitu.CityGuide.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class PlaceDto {
    @NotBlank(message = "Place name cannot be blank")
    @Size(min = 3, max = 64, message = "Place name must be 3-64 characters long")
    private final String name;

    @Size(min = 64, max = 4096, message = "Place description must be 64-4096 characters long")
    private final String description;

    @NotBlank(message = "Street name cannot be blank")
    @Size(min = 3, max = 64, message = "Street name must be 3-64 characters long")
    private final String street;

    @NotBlank(message = "Building number cannot be blank")
    @Size(min = 1, max = 4, message = "Building number must be 1-4 characters long")
    private final String buildingNumber;

    @Size(min = 14, max = 14, message = "Phone number must be exactly 14 characters long. Format: 7-707-123-4567.")
    private final String phoneNumber;

    @Size(min = 3, max = 2048, message = "Website URL must be 3-2048 characters long")
    private final String websiteUrl;

    @NotEmpty(message = "")
    private final List<String> tags;
}
