package com.example.demo.services.country;

import com.example.demo.models.Country;


import java.util.List;

public interface CountryService {
    Country create(Country country);

    List<Country> readAll();

    boolean delete(Long id);

    boolean update(Long id, Country country);
}
