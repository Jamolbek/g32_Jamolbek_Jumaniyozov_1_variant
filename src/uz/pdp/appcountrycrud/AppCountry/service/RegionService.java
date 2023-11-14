package uz.pdp.appcountrycrud.AppCountry.service;

import uz.pdp.appcountrycrud.AppCountry.entity.Region;
import uz.pdp.appcountrycrud.AppCountry.payload.RegionDTO;

import java.util.List;

public interface RegionService {
    List<RegionDTO> all();

    RegionDTO add(RegionDTO regionDTO);

    RegionDTO edit(Integer id, RegionDTO regionDTO);

    String delete(Integer id);

    Region getByIdOrElseThrow(Integer id);
}
