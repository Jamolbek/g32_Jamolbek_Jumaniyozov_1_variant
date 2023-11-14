package uz.pdp.appcountrycrud.AppCountry.service;

import uz.pdp.appcountrycrud.AppCountry.entity.Region;
import uz.pdp.appcountrycrud.AppCountry.payload.RegionDTO;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RegionServiceImpl implements RegionService {

    private static RegionServiceImpl instance;
    public List<Region> regions = Collections.synchronizedList(new ArrayList<>());
    private static Lock lock = new ReentrantLock();

    private static Logger logger = Logger.getLogger("RegionService");

    public static RegionServiceImpl getInstance() {
        if (Objects.isNull(instance)) {
            lock.lock();
            if (Objects.isNull(instance))
                instance = new RegionServiceImpl();
            lock.unlock();
        }
        return instance;
    }

    @Override
    public List<RegionDTO> all() {
        logger.log(Level.INFO, "get all regions");
        List<RegionDTO> regionDTOList = regions
                .stream()
                .map(region -> new RegionDTO(region.getId(), region.getName(), region.getCountry()))
                .collect(Collectors.toList());
        return regionDTOList;
    }

    @Override
    public RegionDTO add(RegionDTO regionDTO) {
        logger.log(Level.INFO, "adding regions DTO by param region DTO -> " + regionDTO);
        int id = regions.size() + 1;
        Region region = new Region(
                id,
                regionDTO.getName(),
                regionDTO.getCountry()
        );

        regions.add(region);
        regionDTO.setId(id);
        return regionDTO;
    }

    @Override
    public RegionDTO edit(Integer id, RegionDTO regionDTO) {
        logger.log(Level.INFO, "Editing Region DTO by param -> " + id);
        Optional<Region> optionalRegion = regions
                .stream()
                .filter(region -> Objects.equals(region.getId(), id))
                .findFirst();

        Region region = optionalRegion.orElseThrow(() -> new RuntimeException("Region not found with id: " + id));

        region.setName(regionDTO.getName());
        regionDTO.setId(region.getId());

        return regionDTO;
    }

    @Override
    public String delete(Integer id) {
        logger.log(Level.INFO, "Deletingg Region by param -> " + id);
        try {
            Region region = getRegionByIdOrElseThrow(id);
            regions.remove(region);
            return "true";

        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @Override
    public Region getByIdOrElseThrow(Integer id) {
        logger.log(Level.INFO, "Getting Region by param id -> " + id);
        Region region = regions
                .stream()
                .filter(oneRegion -> Objects.equals(oneRegion.getId(), id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Region not found with id : " + id));
        return region;
    }

    private Region getRegionByIdOrElseThrow(Integer id) {
        logger.log(Level.INFO, "getting region by id -> " + id);
        Optional<Region> optionalRegion = regions
                .stream()
                .filter(region -> region.getId().equals(id))
                .findFirst();

        Region region = optionalRegion.orElseThrow(() -> new RuntimeException("Region is not found with id : " + id));
        return region;
    }
}
