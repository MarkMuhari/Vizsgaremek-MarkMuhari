package hu.progmasters.codertravel.config;

import lombok.Data;

@Data
public class LogCommand {
    private final String LOG_OK_DELETED = "Http status: OK DELETED";
    private final String LOG_OK = "Http status: OK, response: {}";
    private final String LOG_CREATED = "Http status: CREATED, response: {}";
}
