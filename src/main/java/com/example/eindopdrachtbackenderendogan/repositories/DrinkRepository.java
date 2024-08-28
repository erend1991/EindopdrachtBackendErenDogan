package com.example.eindopdrachtbackenderendogan.repositories;

import com.example.eindopdrachtbackenderendogan.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
}
