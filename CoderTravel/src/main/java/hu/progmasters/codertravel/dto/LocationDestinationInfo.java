package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDestinationInfo {
    private String country;
    private String city;
    private String streetAndNumber;
}
