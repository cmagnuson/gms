package com.carlmagnuson.gms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GardenBed {

    @Id @GeneratedValue @Getter @Setter
    private Long id;
    @Getter @Setter
    private double lat;
    @Getter @Setter
    private double lon;
    @OneToMany(mappedBy = "gardenBed", fetch = FetchType.LAZY)
    @Getter @Setter
    private Collection<Planting> planting;

    protected GardenBed() {}

    public GardenBed(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
        planting = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof GardenBed))
            return false;
        GardenBed bed = (GardenBed) o;
        return Objects.equals(this.id, bed.id) && Objects.equals(this.lat, bed.lat)
                && Objects.equals(this.lon, bed.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.lat, this.lon);
    }

    @Override
    public String toString() {
        return "GardenBed{" + "id=" + this.id + ", lat='" + this.lat + '\'' + ", lon='" + this.lon + '\'' + '}';
    }
}