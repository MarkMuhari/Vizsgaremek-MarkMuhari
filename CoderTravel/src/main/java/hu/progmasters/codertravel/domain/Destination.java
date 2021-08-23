package hu.progmasters.codertravel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    private int pricePerDay;

    @JoinColumn
    @ManyToOne
    private Location location;

    @ManyToOne
    private TravelAgency travelAgency;
}

