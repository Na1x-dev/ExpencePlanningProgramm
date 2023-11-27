package com.example.demo.services.country;

import com.example.demo.models.Country;
import com.example.demo.repositories.country.CountryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryJpaRepository countryJpaRepository;

    @Override
    public Country create(Country country) {
        return countryJpaRepository.save(country);
    }

    @Override
    public List<Country> readAll() {
        return countryJpaRepository.findAll();
    }

    @Override
    public boolean delete(Long id) {
        if (countryJpaRepository.existsById(id)) {
            countryJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Long id, Country country) {
        if (countryJpaRepository.existsById(id)) {
            country.setCountryId(id);
            countryJpaRepository.save(country);
            return true;
        }
        return false;
    }
}
