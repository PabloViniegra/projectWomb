package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepository extends JpaRepository<Countries,Integer> {
}
