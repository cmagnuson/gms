package com.carlmagnuson.gms.repository;

import com.carlmagnuson.gms.model.GardenBed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GardenBedRepository extends JpaRepository<GardenBed, Long> {
}
