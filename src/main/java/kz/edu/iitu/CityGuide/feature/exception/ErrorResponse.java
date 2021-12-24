package kz.edu.iitu.CityGuide.feature.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private final String message;
    private final List<String> details;
}
