package hu.progmasters.codertravel.dto;

import hu.progmasters.codertravel.domain.Destination;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class TravelAgencyInfo {

    private Integer id;

    @NotBlank
    private String name;

    private LocationInfo locationInfo;

    private List<DestinationInfo> destinationInfos;
}
