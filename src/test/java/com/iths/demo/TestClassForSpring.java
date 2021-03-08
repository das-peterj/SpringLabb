package com.iths.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestClassForSpring {

    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddCountryWithPost() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/countries/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");
        CountryDto countryDto = new CountryDto(1, "Sverige");

        var result = restTemplate.postForEntity(baseUrl,countryDto,CountryDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void testGetFromCountriesWithGet() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/countries/1";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/xml");

        var result = restTemplate.getForEntity(baseUrl, CountryDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(new CountryDto(1, "Sverige"));
    }
}

