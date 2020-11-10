package com.carlmagnuson.gms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Planting {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Getter @Setter
    private double lat;
    @Getter @Setter
    private double lon;
    @Getter @Setter
    private LocalDateTime plantedTime;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="garden_bed_id")
    private GardenBed gardenBed;

    protected Planting() {}

    public Planting(Double lat, Double lon, LocalDateTime plantedTime, GardenBed gardenBed) {
        this.lat = lat;
        this.lon = lon;
        this.plantedTime = plantedTime;
        this.gardenBed = gardenBed;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Planting))
            return false;
        Planting planting = (Planting) o;
        return Objects.equals(this.id, planting.id) && Objects.equals(this.lat, planting.lat)
                && Objects.equals(this.lon, planting.lon) && Objects.equals(this.gardenBed.getId(), planting.gardenBed.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.lat, this.lon, this.gardenBed);
    }

    @Override
    public String toString() {
        return "Planting{" + "id=" + this.id + ", lat='" + this.lat + '\'' + ", lon='" + this.lon + '\'' + ", gardenBed='" + this.gardenBed + '\'' + '}';
    }
}
