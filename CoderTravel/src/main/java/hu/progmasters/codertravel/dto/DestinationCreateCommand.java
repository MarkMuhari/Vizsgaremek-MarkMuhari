package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
@NoArgsConstructor
public class DestinationCreateCommand {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Positive
    private int pricePerDay;

    @NotNull
    private LocationInfo locationInfo;
}

