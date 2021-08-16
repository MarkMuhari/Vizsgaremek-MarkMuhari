package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.dto.TravelAgencyCreateCommand;
import hu.progmasters.codertravel.dto.TravelAgencyInfo;
import hu.progmasters.codertravel.service.TravelAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travelagencies")
public class TravelAgencyController {

    @Autowired
    private TravelAgencyService agencyService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelAgencyInfo findTravelAgencyById(@PathVariable("id") Integer id) {
        return agencyService.findTravelAgencyById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelAgencyInfo createTravelAgency(@RequestBody TravelAgencyCreateCommand createCommand) {
        return agencyService.saveTravelAgency(createCommand);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelAgencyInfo modifyTravelAgency(@PathVariable("id") Integer id,
                                               @RequestBody TravelAgencyCreateCommand updateCommand) {
        return agencyService.updateTravelAgency(id, updateCommand);
    }
}
