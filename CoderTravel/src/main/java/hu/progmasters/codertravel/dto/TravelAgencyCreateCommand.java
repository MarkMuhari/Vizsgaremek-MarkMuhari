package hu.progmasters.codertravel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TravelAgencyCreateCommand {

    @NotBlank(message = "Travel Agency name must not be blank!")
    @Schema(description = "Travel Agency name.", example = "Coder Traveler")
    private String name;

    @NotNull(message = "Location id must not be null!")
    @Schema(description = "location of id", example = "1")
    private Integer locationID;
}
