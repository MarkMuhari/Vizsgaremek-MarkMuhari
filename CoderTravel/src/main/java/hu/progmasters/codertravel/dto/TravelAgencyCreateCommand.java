package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class TravelAgencyCreateCommand {

    @NotBlank
    private String name;

    private Integer locationID;
}
