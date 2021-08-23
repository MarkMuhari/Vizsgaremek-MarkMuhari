package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.config.LogCommand;
import hu.progmasters.codertravel.dto.DestinationCreateCommand;
import hu.progmasters.codertravel.dto.DestinationInfo;
import hu.progmasters.codertravel.exceptionhandling.DestinationNotFoundException;
import hu.progmasters.codertravel.service.DestinationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/destinations")
public class DestinationController {

    private DestinationService destinationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationController.class);
    private static final LogCommand logCommand = new LogCommand();

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @Operation(summary = "Find all active Destinations")
    @ApiResponse(
            responseCode = "200",
            description = "Return all destinations.",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = DestinationInfo.class))))
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DestinationInfo> findAllDestinations() {
        List<DestinationInfo> allDestinations = destinationService.findAllDestinations();
        LOGGER.info(logCommand.getLOG_OK(), allDestinations);
        return allDestinations;

    }

    @Operation(summary = "Find a Destination by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the selected destination.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DestinationInfo.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Destination not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = DestinationNotFoundException.class)))
            )})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DestinationInfo findDestinationById(@Parameter(description = "Id of the destination", example = "1")
                                               @PathVariable("id") Integer id) {
        DestinationInfo destinationById = destinationService.findDestinationById(id);
        LOGGER.info(logCommand.getLOG_OK(), destinationById);
        return destinationById;
    }


    @Operation(summary = "Create a new Destination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return the created destination.", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = DestinationInfo.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =
            @Schema(implementation = DestinationNotFoundException.class)
            )))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DestinationInfo createDestination(@RequestBody @Valid DestinationCreateCommand createCommand) {
        DestinationInfo destinationInfo = destinationService.saveDestination(createCommand);
        LOGGER.info(logCommand.getLOG_CREATED(), destinationInfo);
        return destinationInfo;
    }

    @Operation(summary = "Modify a location")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the modified destination.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DestinationInfo.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Destination not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = DestinationNotFoundException.class)))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request on validation error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = DestinationNotFoundException.class))))})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DestinationInfo modifyDestination(@Parameter(description = "Id of the destination", example = "1")
                                             @PathVariable("id") Integer id,
                                             @RequestBody @Valid DestinationCreateCommand updateCommand) {
        DestinationInfo updateDestination = destinationService.updateDestination(id, updateCommand);
        LOGGER.info(logCommand.getLOG_OK(), updateDestination);
        return updateDestination;
    }

    @Operation(summary = "Delete a destination")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Destination deleted"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Destination not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = DestinationNotFoundException.class)))
            )})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDestination(@Parameter(description = "Id of the destination", example = "1")
                                  @PathVariable("id") Integer id) {
        LOGGER.info(logCommand.getLOG_OK_DELETED());
        destinationService.removeDestination(id);
    }
}
