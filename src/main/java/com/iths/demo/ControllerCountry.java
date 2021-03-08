package com.iths.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
class CountryController {

    private final Service service;

    public CountryController(Service service) {
        this.service = service;
    }

    @GetMapping("/countries")
    public List<CountryDto> all() {
        return service.getAllCountries();
    }

    @GetMapping("/countries/{id}")
    public CountryDto one(@PathVariable Long id) {
        return service.getOne(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Id " + id + " not found."));
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto create(@RequestBody CountryDto country) {
        return service.createCountry(country);
    }

    @RequestMapping("/s/{searchTerm}")
    public List<CountryDto> search(@PathVariable String searchTerm) {
        return service.search(searchTerm);
    }

    @DeleteMapping("/countries/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/countries/{id}")
    public CountryDto replace(@RequestBody CountryDto countryDto, @PathVariable Long id) {
        return service.replace(id, countryDto);
    }

    @PatchMapping("/countries/{id}")
    public CountryDto update(@RequestBody CountryDto countryDto, @PathVariable Long id) {
        return service.update(id, countryDto);
    }


}
