package uz.pdp.appcountrycrud.AppCountry.payload;

import uz.pdp.appcountrycrud.AppCountry.entity.Region;

import java.time.LocalDate;
import java.util.List;

public class CountryDTO {
    private Integer id;
    private String name;
    private String flagURL;
    private LocalDate establishmentDate;

    private String descriptionFilePath;
    private double square;
    private Integer regionId;
    private List<Region> regions;

    public CountryDTO(Integer id, String name, String flagURL, LocalDate establishmentDate,String descriptionFilePath, double square) {
        this.id = id;
        this.name = name;
        this.flagURL = flagURL;
        this.establishmentDate = establishmentDate;
        this.descriptionFilePath=descriptionFilePath;
        this.square = square;
        this.regionId = regionId;
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

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public String getDescriptionFilePath() {
        return descriptionFilePath;
    }

    public void setDescriptionFilePath(String descriptionFilePath) {
        this.descriptionFilePath = descriptionFilePath;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flagURL='" + flagURL + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", descriptionFilePath='" + descriptionFilePath + '\'' +
                ", square=" + square +
                ", regionId=" + regionId +
                ", regions=" + regions +
                '}';
    }
}
