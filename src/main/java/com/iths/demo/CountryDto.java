package com.iths.demo;

import java.util.Objects;

public class CountryDto {
    private long id;
    private String country;

    public CountryDto(long id, String country) {
        this.id = id;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryDto that = (CountryDto) o;
        return id == that.id && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country);
    }
}
