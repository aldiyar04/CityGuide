package kz.edu.iitu.CityGuide.controller.dto.response;

import kz.edu.iitu.CityGuide.controller.dto.request.PlaceCreationDto;
import kz.edu.iitu.CityGuide.feature.validation.CheckId;
import kz.edu.iitu.CityGuide.feature.validation.CheckRatingValue;
import kz.edu.iitu.CityGuide.repository.entity.Comment;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class PlaceDto extends PlaceCreationDto {
    @CheckId
    private final Long id;

    @CheckRatingValue
    private final BigDecimal averageRating;

    private final List<Comment> comments;

    public PlaceDto(String name, String description, String street, String buildingNumber, String phoneNumber,
                     String websiteUrl, List<String> tags, Long id, BigDecimal averageRating, List<Comment> comments) {
        super(name, description, street, buildingNumber, phoneNumber, websiteUrl, tags);
        this.id = id;
        this.averageRating = averageRating;
        this.comments = comments;
    }
}
