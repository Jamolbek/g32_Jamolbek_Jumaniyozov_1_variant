package uz.pdp.appcountrycrud.AppCountry;

import uz.pdp.appcountrycrud.AppCountry.payload.CountryDTO;
import uz.pdp.appcountrycrud.AppCountry.payload.RegionDTO;
import uz.pdp.appcountrycrud.AppCountry.service.CountryService;
import uz.pdp.appcountrycrud.AppCountry.service.CountryServiceImpl;
import uz.pdp.appcountrycrud.AppCountry.service.RegionService;
import uz.pdp.appcountrycrud.AppCountry.service.RegionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        //URL GitLab ::


        CountryServiceImpl countryService = CountryServiceImpl.getInstance();
        countryService.deserialize();

        List<RegionDTO> regionDTOList = generateRegions();

        System.out.println(regionDTOList);

        List<CountryDTO> countryDTOList = generateCountry(regionDTOList);

        System.out.println(countryDTOList);

        Random random = new Random();
        int index = random.nextInt(countryDTOList.size());
        CountryDTO countryDTO = countryDTOList.get(index);

        String read = countryService.read(countryDTO.getId());
        System.out.println("read -> " + read);

        int genreIndex = random.nextInt(regionDTOList.size());
        RegionDTO regionDTO = regionDTOList.get(genreIndex);
        List<CountryDTO> countriesByRegion = countryService.getByRegion(regionDTO.getId());
        System.out.println("countries by region -> " + countriesByRegion);

        boolean serialize = countryService.serialize();
        System.out.println(serialize);

        boolean deserialize = countryService.deserialize();
        System.out.println(deserialize);

    }

    private static List<CountryDTO> generateCountry(List<RegionDTO> regionDTOList) {
        CountryService countryService = CountryServiceImpl.getInstance();
        List<CountryDTO> countryDTOList = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int regionIndex = random.nextInt(regionDTOList.size());
            RegionDTO regionDTO = regionDTOList.get(regionIndex);
            CountryDTO countryDTO = countryService.add(new CountryDTO(
                    null,
                    "Country name -> " + i,
                    "Flag URL -> " + i,
                    LocalDate.of(1991, 8, 31),
                    "vountryInfo/countryAbout.txt",
                    regionDTO.getId()
            ));
            countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }

    private static List<RegionDTO> generateRegions() {
        RegionService regionService = RegionServiceImpl.getInstance();

        List<RegionDTO> regionDTOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            RegionDTO regionDTO = regionService.add(new RegionDTO(null, "Region -> " + i, "Country -> "+i));
            regionDTOList.add(regionDTO);
        }
        return regionDTOList;
    }
}