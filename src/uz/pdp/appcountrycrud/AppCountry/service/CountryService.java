package uz.pdp.appcountrycrud.AppCountry.service;

import uz.pdp.appcountrycrud.AppCountry.payload.CountryDTO;

import java.util.List;

public interface CountryService {
    //1
    List<CountryDTO> getByRegion(Integer regionId);

    //2
    CountryDTO getById(Integer id);

    //3
    CountryDTO add(CountryDTO bookDTO);

    //4
    CountryDTO edit(Integer id, CountryDTO bookDTO);

    //5
    boolean delete(Integer id);

    //6
    String read(Integer id);

    //7
    boolean serialize();

    //8
    boolean deserialize();

}
