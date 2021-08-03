package hu.progmasters.codertravel.exceptionhandling;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LocationNotFoundException extends RuntimeException {
    private final Integer locationId;
}
