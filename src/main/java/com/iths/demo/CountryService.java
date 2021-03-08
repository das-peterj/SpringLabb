package com.iths.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements com.iths.demo.Service {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryDto> getAllCountries() { return countryMapper.mapp(countryRepository.findAll());
    }

    @Override
    public Optional<CountryDto> getOne(Long id) {
        if (countryRepository.existsById(id)) {
            return countryMapper.mapp(countryRepository.findById(id));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID: " + id + " doesn't exist in the database.");
    }

    @Override
    public CountryDto createCountry(CountryDto country) {
        if (country.getCountry().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No country name was submitted by the user.");
        }
        return countryMapper.mapp(countryRepository.save(countryMapper.mapp(country)));
    }

    @Override
    public List<CountryDto> search(String searchTerm) {
        if (searchTerm.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country " + searchTerm + " could not be found within the database.");
        }
        return countryMapper.mapp(countryRepository.findAllByCountry(searchTerm));
    }

    @Override
    public void delete(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID: " + id + " doesn't exist in the database.");
    }

    @Override
    public CountryDto replace(Long id, CountryDto countryDto) {
        Optional<Country> country = countryRepository.findById(id);

        if(country.isPresent()) {
            Country newCountry = country.get();
            newCountry.setCountry(countryDto.getCountry());

            return countryMapper.mapp(countryRepository.save(newCountry));
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID: " + id + " doesn't exist in the database");
    }

    @Override
    public CountryDto update(Long id, CountryDto countryDto) {
        Optional<Country> country = countryRepository.findById(id);

        if(country.isPresent()) {
            Country newCountry = country.get();

            if(countryDto != null) {
                newCountry.setCountry(countryDto.getCountry());
            }
            return countryMapper.mapp(countryRepository.save(newCountry));
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID: " + id + " doesn't exist in the database.");
    }


}




