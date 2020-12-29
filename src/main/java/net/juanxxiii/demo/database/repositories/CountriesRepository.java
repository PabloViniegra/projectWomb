package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Integer> {
    @Query(value = "SELECT * FROM Countries c WHERE c.nicename=:nicename", nativeQuery = true)
    Countries findByName(@Param("nicename") String name);
}
