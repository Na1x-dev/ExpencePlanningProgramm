package com.example.demo.repositories.courier;


import com.example.demo.models.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierJpaRepository extends JpaRepository<Courier, Long> {

}