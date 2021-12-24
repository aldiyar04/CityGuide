package kz.edu.iitu.CityGuide.feature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RecordAlreadyExistsException extends ApiException {
    public RecordAlreadyExistsException(String exception) {
        super(exception);
    }
}
