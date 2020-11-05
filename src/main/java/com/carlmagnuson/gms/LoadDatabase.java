package com.carlmagnuson.gms;

import com.carlmagnuson.gms.model.GardenBed;
import com.carlmagnuson.gms.repository.GardenBedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GardenBedRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new GardenBed(0.1, 0.2)));
            log.info("Preloading " + repository.save(new GardenBed(0.3, 0.4)));
        };
    }
}