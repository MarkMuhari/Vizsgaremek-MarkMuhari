package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

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
    public LocationInfo createLocation(@RequestBody @Valid LocationCreateCommand createCommand) {
        return locationService.saveLocation(createCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationInfo modifyLocation(@PathVariable("id") Integer id, @RequestBody LocationCreateCommand updateCommand) {
        return locationService.updateLocation(id, updateCommand);
    }
}
