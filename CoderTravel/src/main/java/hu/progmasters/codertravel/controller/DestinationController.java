package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.DestinationCreateCommand;
import hu.progmasters.codertravel.dto.DestinationInfo;
import hu.progmasters.codertravel.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DestinationInfo> findAllDestinations() {
        return destinationService.findAllDestinations();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DestinationInfo findDestinationById(@PathVariable("id") Integer id) {
        return destinationService.findDestinationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DestinationInfo createDestination(@RequestBody DestinationCreateCommand createCommand) {
        return destinationService.saveDestination(createCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DestinationInfo modifyDestination(@PathVariable("id") Integer id,
                                             @RequestBody DestinationCreateCommand updateCommand) {
        return destinationService.updateDestination(id, updateCommand);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDestination(@PathVariable("id") Integer id) {
        destinationService.removeDestination(id);
    }
}
