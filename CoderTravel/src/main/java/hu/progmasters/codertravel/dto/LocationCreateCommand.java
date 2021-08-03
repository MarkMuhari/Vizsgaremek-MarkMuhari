package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LocationCreateCommand {

    @NotBlank
    private String iso;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String streetAndNumber;

}
