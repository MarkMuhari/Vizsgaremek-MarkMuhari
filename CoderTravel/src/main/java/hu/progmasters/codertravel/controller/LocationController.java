package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.config.LogCommand;
import hu.progmasters.codertravel.dto.LocationCreateCommand;
import hu.progmasters.codertravel.dto.LocationInfo;
import hu.progmasters.codertravel.exceptionhandling.LocationNotFoundException;
import hu.progmasters.codertravel.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    private static final LogCommand logCommand = new LogCommand();

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Operation(summary = "Find all Location.")
    @ApiResponse(
            responseCode = "200",
            description = "Return all location.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = LocationInfo.class))))
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocationInfo> findAllLocations() {
        LOGGER.info(logCommand.getLOG_OK());
        return locationService.findAllLocations();
    }

    @Operation(summary = "Find a Location by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the selected location.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LocationInfo.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Location not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LocationNotFoundException.class))))})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationInfo findLocationById(@PathVariable("id") Integer id) {

        LOGGER.info(logCommand.getLOG_OK());
        return locationService.findLocationById(id);
    }


    @Operation(summary = "Create a new Location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return the created location.", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = LocationInfo.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =
            @Schema(implementation = LocationNotFoundException.class)
            )))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationInfo createLocation(@RequestBody @Valid LocationCreateCommand createCommand) {
        LocationInfo locationInfo = locationService.saveLocation(createCommand);
        LOGGER.info(logCommand.getLOG_CREATED(), locationInfo);
        return locationInfo;
    }

    @Operation(summary = "Modify a location")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the modified location.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LocationInfo.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Location not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LocationNotFoundException.class)))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request on validation error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = LocationNotFoundException.class))))})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationInfo modifyLocation(@PathVariable("id") Integer id,
                                       @RequestBody @Valid LocationCreateCommand updateCommand) {
        LOGGER.info(logCommand.getLOG_OK());
        return locationService.updateLocation(id, updateCommand);
    }
}
