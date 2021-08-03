package hu.progmasters.codertravel;

import hu.progmasters.codertravel.service.InitDbService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoderTravelApplication {

    private static InitDbService dbService;

    public static void main(String[] args) {
        SpringApplication.run(CoderTravelApplication.class, args);
        dbService.initDb();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
