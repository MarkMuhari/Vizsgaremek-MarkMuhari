package hu.progmasters.codertravel.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DestinationInfo {

    private Integer id;

    private String title;

    private String description;

    private int pricePerDay;

    private LocationInfo locationInfo;

    private TravelAgencyInfo agencyInfo;
}

