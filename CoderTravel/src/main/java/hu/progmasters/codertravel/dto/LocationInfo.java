package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationInfo {

    private Integer id;
    private String iso;
    private String country;
    private String city;
    private String streetAndNumber;

}
