package com.iths.demo;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<CountryDto> getAllCountries();

    Optional<CountryDto> getOne(Long id);

    CountryDto createCountry(CountryDto country);

    void delete(Long id);

    CountryDto replace(Long id, CountryDto countryDto);

    CountryDto update(Long id, CountryDto countryDto);

    List<CountryDto> search(String search);
}
