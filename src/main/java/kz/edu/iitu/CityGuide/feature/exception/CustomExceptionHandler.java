package kz.edu.iitu.CityGuide.feature.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@SuppressWarnings({"unchecked","rawtypes"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ex.printStackTrace();

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ApiException.class, AccessDeniedException.class})
    public final ResponseEntity<Object> handleApiExceptions(Exception ex, WebRequest request) {
        HttpStatus httpStatus;
        String message;
        List<String> details = new ArrayList<>();
        if (ex instanceof RecordNotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
            message = "Record Not Found";
        } else if (ex instanceof RecordAlreadyExistsException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            message = "Record Already Exists";
        } else {
            httpStatus = HttpStatus.FORBIDDEN;
            message = "Access Denied";
            details.add("You don't have the role (USER / ADMIN) required to access this resource");
        }
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(message, details);
        return new ResponseEntity(error, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
