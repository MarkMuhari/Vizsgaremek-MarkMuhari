package hu.progmasters.codertravel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
public class DestinationCreateCommand {

    @NotBlank(message = "Title must not be blank!")
    @Schema(description = "Destination main title", example = "Budapest Aug 20")
    private String title;

    @NotBlank(message = "Description must not be blank!")
    @Schema(description = "Destination of purpose",
            example = "Very nice city and interesting programs are waiting for you.")
    private String description;

    @Positive(message = "Only a positive number is acceptable.")
    @Schema(description = "Daily price for the program.", example = "4500")
    private int pricePerDay;

    @NotNull(message = "Location id must not be null!")
    @Schema(description = "location of id", example = "1")
    private Integer locationId;

    @NotNull(message = "Location id must not be null!")
    @Schema(description = "Travel Agency of id", example = "1")
    private Integer agencyId;
}

