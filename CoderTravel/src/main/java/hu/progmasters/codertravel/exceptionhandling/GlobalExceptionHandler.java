package hu.progmasters.codertravel.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ISNOTFOUND = " is not found!";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleLocationNotFound(LocationNotFoundException exception) {
        ValidationError validationError = new ValidationError("locationId",
                "Location with id: " + exception.getLocationId() + ISNOTFOUND);
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleDestinationNotFound(DestinationNotFoundException exception) {
        ValidationError validationError = new ValidationError("destinationId",
                "Destination with id: " + exception.getDestinationId() + ISNOTFOUND);
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TravelAgencyNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleTravelAgencyNotFound(TravelAgencyNotFoundException exception) {
        ValidationError validationError = new ValidationError("travelAgencyId",
                "Travel Agency with id: " + exception.getTravelAgencyId() + ISNOTFOUND);
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }
}
