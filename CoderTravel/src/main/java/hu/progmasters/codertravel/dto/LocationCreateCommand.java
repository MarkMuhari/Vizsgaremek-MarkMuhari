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
    @Schema(description = "Location of iso code.", example = "HU")
    private String iso;

    @NotBlank(message = "Country must not be blank!")
    @Schema(description = "Location of country.", example = "Hungary")
    private String country;

    @NotBlank(message = "City must not be blank!")
    @Schema(description = "Location of city.", example = "Budapest")
    private String city;

    @NotBlank(message = "Street and number must not be blank!")
    @Schema(description = "Location of street and number.", example = "Lenkey street 7.")
    private String streetAndNumber;

}
