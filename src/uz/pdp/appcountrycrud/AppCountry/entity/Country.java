package uz.pdp.appcountrycrud.AppCountry.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Country implements Serializable {
    private Integer id;
    private String name;
    private String flagURL;

    private String descriptionFilePath;
    private LocalDate establishmentDate;
    private double square;
    private List<Region> regions;

    public Country(Integer id, String name, String flagURL, LocalDate establishmentDate,String descriptionFilePath, double square, List<Region> regions) {
        this.id = id;
        this.name = name;
        this.flagURL = flagURL;
        this.establishmentDate = establishmentDate;
        this.descriptionFilePath=descriptionFilePath;
        this.square = square;
        this.regions = regions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagURL() {
        return flagURL;
    }

    public void setFlagURL(String flagURL) {
        this.flagURL = flagURL;
    }

    public LocalDate getEstablishmentDate() {
        return establishmentDate;
    }

    public String getDescriptionFilePath() {
        return descriptionFilePath;
    }

    public void setDescriptionFilePath(String descriptionFilePath) {
        this.descriptionFilePath = descriptionFilePath;
    }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flagURL='" + flagURL + '\'' +
                ", descriptionFilePath='" + descriptionFilePath + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", square=" + square +
                ", regions=" + regions +
                '}';
    }
}
