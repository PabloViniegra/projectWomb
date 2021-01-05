package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.FavouritesWomb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesWombRepository extends JpaRepository<FavouritesWomb, Integer> {
}
