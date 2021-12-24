package kz.edu.iitu.CityGuide.feature.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
