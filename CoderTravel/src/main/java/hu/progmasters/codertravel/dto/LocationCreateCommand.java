package hu.progmasters.codertravel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LocationCreateCommand {

    @NotBlank(message = "ISO must not be blank!")
    @Min(value = 2, message = "ISO must be minimum 2 characters")
    @Max(value = 4, message = "ISO must be maximum 4 characters")
    @Schema(description = "Location of iso code.", example = "HU")
    private String iso;

    @NotBlank(message = "Country must not be blank!")
    @Max(value = 40, message = "Travel Agency must be max 40 characters.")
    @Schema(description = "Location of country.", example = "Hungary")
    private String country;

    @NotBlank(message = "City must not be blank!")
    @Max(value = 50, message = "Travel Agency must be max 50 characters.")
    @Schema(description = "Location of city.", example = "Budapest")
    private String city;

    @NotBlank(message = "Street and number must not be blank!")
    @Max(value = 100, message = "Travel Agency must be max 100 characters.")
    @Schema(description = "Location of street and number.", example = "Lenkey street 7.")
    private String streetAndNumber;

}
