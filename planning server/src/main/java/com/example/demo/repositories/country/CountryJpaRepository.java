package com.example.demo.repositories.country;

import com.example.demo.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryJpaRepository extends JpaRepository<Country, Long> {

}