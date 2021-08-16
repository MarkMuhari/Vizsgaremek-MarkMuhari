package hu.progmasters.codertravel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private int pricePerDay;

    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @ManyToOne
    private TravelAgency travelAgency;
}

