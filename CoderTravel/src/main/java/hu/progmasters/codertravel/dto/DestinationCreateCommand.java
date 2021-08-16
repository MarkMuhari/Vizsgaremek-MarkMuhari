package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DestinationCreateCommand {

    //    @NotBlank
    private String title;

    //    @NotBlank
    private String description;

    //    @Positive
    private int pricePerDay;

    //    @NotNull
    private Integer locationId;

    private Integer agencyId;
}

