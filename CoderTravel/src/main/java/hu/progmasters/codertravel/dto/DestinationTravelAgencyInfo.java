package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DestinationTravelAgencyInfo {

    private Integer id;

    private String title;

    private String description;

    private int pricePerDay;

    private LocationDestinationInfo locationDestinationInfo;

    private TravelAgencyDestinationInfo agencyDestinationInfo;
}
