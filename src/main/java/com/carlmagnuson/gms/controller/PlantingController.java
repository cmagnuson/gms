package com.carlmagnuson.gms.controller;

import com.carlmagnuson.gms.model.Planting;
import com.carlmagnuson.gms.repository.PlantingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantingController {

    final private PlantingRepository plantingRepository;

    PlantingController(PlantingRepository plantingRepository){
        this.plantingRepository = plantingRepository;
    }

    @GetMapping("/planting")
    List<Planting> list(){
        return plantingRepository.findAll();
    }

    @PostMapping("/planting")
    Planting newPlanting(@RequestBody Planting planting) {
        return plantingRepository.save(planting);
    }

    @GetMapping("/planting/{id}")
    Planting getById(@PathVariable long id){
        return plantingRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/planting/{id}")
    Planting replacePlanting(@RequestBody Planting newPlanting, @PathVariable Long id) {

        return plantingRepository.findById(id)
                .map(planting -> {
                    planting.setLat(newPlanting.getLat());
                    planting.setLon(newPlanting.getLon());
                    planting.setPlantedTime(newPlanting.getPlantedTime());
                    return plantingRepository.save(planting);
                })
                .orElseGet(() -> {
                    newPlanting.setId(id);
                    return plantingRepository.save(newPlanting);
                });
    }

    @DeleteMapping("/planting/{id}")
    void deletePlanting(@PathVariable Long id) {
        plantingRepository.deleteById(id);
    }
}
