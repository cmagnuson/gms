package com.carlmagnuson.gms;

import com.carlmagnuson.gms.model.GardenBed;
import com.carlmagnuson.gms.model.Planting;
import com.carlmagnuson.gms.repository.GardenBedRepository;
import com.carlmagnuson.gms.repository.PlantingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GardenBedRepository repository, PlantingRepository plantingRepository) {

        return args -> {
            log.info("Preloading " + repository.save(new GardenBed(0.1, 0.2)));
            GardenBed g2 = new GardenBed(0.3, 0.4);
            log.info("Preloading " + repository.save(g2));
            log.info("Preloading " + plantingRepository.save(new Planting(0.3, 0.3, LocalDateTime.MAX, g2)));
        };
    }
}