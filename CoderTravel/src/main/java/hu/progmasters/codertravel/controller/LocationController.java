package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocationInfo> findAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationInfo findLocationById(@PathVariable("id") Integer id) {
        return locationService.findLocationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationInfo createLocation(@RequestBody LocationCreateCommand createCommand) {
        return locationService.saveLocation(createCommand);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public LocationInfo updateLocation(@RequestBody LocationCreateCommand updateCommand) {
        return locationService.updateLocation(updateCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocation(@PathVariable("id") Integer id) {
        locationService.deleteLocation(id);
    }
}
