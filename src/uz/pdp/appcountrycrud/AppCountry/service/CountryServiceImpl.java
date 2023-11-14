package uz.pdp.appcountrycrud.AppCountry.service;

import uz.pdp.appcountrycrud.AppCountry.entity.Country;
import uz.pdp.appcountrycrud.AppCountry.entity.Region;
import uz.pdp.appcountrycrud.AppCountry.payload.CountryDTO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CountryServiceImpl implements CountryService {

    private static CountryServiceImpl instance;
    public List<Country> countries = Collections.synchronizedList(new ArrayList<>());
    private static Lock lock = new ReentrantLock();
    private static Logger logger = Logger.getLogger("CountryService");

    private CountryServiceImpl() {

    }

    public static CountryServiceImpl getInstance() {

        if (Objects.isNull(instance)) {
            lock.lock();
            if (Objects.isNull(instance)) {
                instance = new CountryServiceImpl();
            }
            lock.unlock();
        }
        return instance;
    }


    @Override
    public List<CountryDTO> getByRegion(Integer regionId) {
        logger.log(Level.INFO, "get countries by region param ->" + regionId);

        List<CountryDTO> countryDTOList = countries
                .stream()
                .filter(country -> country.getId().equals(regionId))
                .map(country -> toDTO(country))
                .collect(Collectors.toList());

        return countryDTOList;
    }


    @Override
    public CountryDTO getById(Integer id) {
        logger.log(Level.INFO, "get countries by region param ->" + id);

        Country country = getCountryByIdOrElseThrow(id);

        CountryDTO countryDTO = toDTO(country);
        return countryDTO;
    }


    @Override
    public CountryDTO add(CountryDTO countryDTO) {
        logger.log(Level.INFO, "add countryDTO by countryDTO param ->" + countryDTO);

        int id = countries.size() + 1;

        List<Region> regions = RegionServiceImpl.getInstance().regions;

        RegionService regionService = RegionServiceImpl.getInstance();

        Region region = regionService.getByIdOrElseThrow(countryDTO.getId());

        Country country = new Country(
                id,
                countryDTO.getName(),
                countryDTO.getFlagURL(),
                countryDTO.getEstablishmentDate(),
                countryDTO.getDescriptionFilePath(),
                countryDTO.getSquare(),
                countryDTO.getRegions()
        );

        countries.add(country);
        return toDTO(country);
    }

    @Override
    public CountryDTO edit(Integer id, CountryDTO countryDTO) {
        logger.log(Level.INFO, "edit countryDTO by country param ->" + countryDTO);

        Country country = getCountryByIdOrElseThrow(id);

        country.setName(countryDTO.getName());
        country.setEstablishmentDate(countryDTO.getEstablishmentDate());
        country.setFlagURL(countryDTO.getFlagURL());
        country.setSquare(countryDTO.getSquare());
        country.setRegions(countryDTO.getRegions());

        RegionService regionService = RegionServiceImpl.getInstance();
        Region region = regionService.getByIdOrElseThrow(countryDTO.getRegionId());
        country.setRegions((List<Region>) region);

        CountryDTO countryDTO1 = toDTO(country);
        return countryDTO1;
    }

    @Override
    public boolean delete(Integer id) {
        logger.log(Level.INFO, "delete country by country param ->" + id);

        try {
            Country country = getCountryByIdOrElseThrow(id);
            countries.remove(country);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String read(Integer id) {
        logger.log(Level.INFO, "read countries by country param ->" + id);
        Country country = getCountryByIdOrElseThrow(id);

        Path path = Paths.get(country.getDescriptionFilePath());

        return readFileViaFileInputStream(path);
    }

    @Override
    public boolean serialize() {
        logger.log(Level.INFO, "serialize countries by country param");
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("countries/countries.txt"))) {

            objectOutputStream.writeObject(countries);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deserialize() {
        logger.log(Level.INFO, "deserialize countries by country param");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("countries/countries.txt"))) {

            countries = (List<Country>) objectInputStream.readObject();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CountryDTO toDTO(Country country) {
        logger.log(Level.INFO, "transfer to DTO by param -> " + country);
        return new CountryDTO(
                country.getId(),
                country.getName(),
                country.getFlagURL(),
                country.getEstablishmentDate(),
                country.getDescriptionFilePath(),
                country.getSquare()
        );
    }

    private Country getCountryByIdOrElseThrow(Integer id) {
        logger.log(Level.INFO, "getting country by id -> " + id);
        Optional<Country> optionalCountry = countries
                .stream()
                .filter(country -> country.getId().equals(id))
                .findFirst();

        Country country = optionalCountry.orElseThrow(() -> new RuntimeException("Country is not found with id : " + id));
        return country;
    }

    private String readFileViaFileInputStream(Path path) {
        logger.log(Level.INFO, "reading about country by param -> " + path);
        try (InputStream inputStream = new FileInputStream(path.toFile())) {

            byte[] bytes = inputStream.readAllBytes();

            return new String(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
