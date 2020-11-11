package com.carlmagnuson.gms.repository;

import com.carlmagnuson.gms.model.GardenBed;
import com.carlmagnuson.gms.model.Planting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantingRepository extends JpaRepository<Planting, Long> {
}
