package kz.edu.iitu.CityGuide.feature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends ApiException {
    public RecordNotFoundException(String exception) {
        super(exception);
    }
}
