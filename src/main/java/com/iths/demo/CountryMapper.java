package com.iths.demo;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CountryMapper {
    public CountryMapper() {}

    public CountryDto mapp(Country country) {
        return new CountryDto(country.getId(), country.getCountry());
    }

    public Country mapp(CountryDto countryDto) {
        return new Country(countryDto.getId(), countryDto.getCountry());
    }

    public Optional<CountryDto> mapp(Optional<Country> optionalCountry) {
        if (optionalCountry.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapp(optionalCountry.get()));
    }

    public List<CountryDto> mapp(List<Country> all) {
        return all .stream().map(this::mapp).collect(Collectors.toList());
    }
}
