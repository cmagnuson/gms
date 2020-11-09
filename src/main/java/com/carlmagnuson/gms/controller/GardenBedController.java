package com.carlmagnuson.gms.controller;

import com.carlmagnuson.gms.model.GardenBed;
import com.carlmagnuson.gms.repository.GardenBedRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GardenBedController {

    final private GardenBedRepository gardenBedRepository;

    GardenBedController(GardenBedRepository gardenBedRepository){
        this.gardenBedRepository = gardenBedRepository;
    }

    @GetMapping("/gardenBed")
    List<GardenBed> list(){
        return gardenBedRepository.findAll();
    }

    @PostMapping("/gardenBed")
    GardenBed newGardenBed(@RequestBody GardenBed newGardenBed) {
        return gardenBedRepository.save(newGardenBed);
    }

    @GetMapping("/gardenBed/{id}")
    GardenBed getById(@PathVariable long id){
        return gardenBedRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/gardenBed/{id}")
    GardenBed replaceGardenBed(@RequestBody GardenBed newGardenBed, @PathVariable Long id) {

        return gardenBedRepository.findById(id)
                .map(gardenBed -> {
                    gardenBed.setLat(newGardenBed.getLat());
                    gardenBed.setLon(newGardenBed.getLon());
                    return gardenBedRepository.save(gardenBed);
                })
                .orElseGet(() -> {
                    newGardenBed.setId(id);
                    return gardenBedRepository.save(newGardenBed);
                });
    }

    @DeleteMapping("/gardenBed/{id}")
    void deleteEmployee(@PathVariable Long id) {
        gardenBedRepository.deleteById(id);
    }
}
