package hu.progmasters.codertravel.controller;

import hu.progmasters.codertravel.config.LogCommand;
import hu.progmasters.codertravel.dto.TravelAgencyCreateCommand;
import hu.progmasters.codertravel.dto.TravelAgencyInfo;
import hu.progmasters.codertravel.exceptionhandling.TravelAgencyNotFoundException;
import hu.progmasters.codertravel.service.TravelAgencyService;
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

@RestController
@RequestMapping("/api/travelagencies")
public class TravelAgencyController {

    private TravelAgencyService agencyService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelAgencyController.class);
    private static final LogCommand logCommand = new LogCommand();

    public TravelAgencyController(TravelAgencyService agencyService) {
        this.agencyService = agencyService;
    }

    @Operation(summary = "Find a Travel Agency by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the selected Travel Agency.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TravelAgencyInfo.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Travel Agency not found",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TravelAgencyNotFoundException.class))
                    ))})
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelAgencyInfo findTravelAgencyById(@PathVariable("id") Integer id) {
        TravelAgencyInfo travelAgencyById = agencyService.findTravelAgencyById(id);
        LOGGER.info(logCommand.getLOG_OK(), travelAgencyById);
        return travelAgencyById;
    }

    @Operation(summary = "Create a new Travel agency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Return the created destination.", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TravelAgencyInfo.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad request!", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema =
            @Schema(implementation = TravelAgencyNotFoundException.class)
            )))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelAgencyInfo createTravelAgency(@RequestBody @Valid TravelAgencyCreateCommand createCommand) {
        TravelAgencyInfo travelAgencyInfo = agencyService.saveTravelAgency(createCommand);
        LOGGER.info(logCommand.getLOG_CREATED(), travelAgencyInfo);
        return travelAgencyInfo;
    }

    @Operation(summary = "Modify a Travel Agency")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return the modified Travel Agency.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TravelAgencyInfo.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Travel Agency not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TravelAgencyNotFoundException.class))
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request on validation error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TravelAgencyNotFoundException.class))
                    ))})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TravelAgencyInfo modifyTravelAgency(@PathVariable("id") Integer id,
                                               @RequestBody @Valid TravelAgencyCreateCommand updateCommand) {
        TravelAgencyInfo travelAgencyInfo = agencyService.updateTravelAgency(id, updateCommand);
        LOGGER.info(logCommand.getLOG_OK(), travelAgencyInfo);
        return travelAgencyInfo;
    }
}
