package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.FavouritesWomb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesWombRepository extends JpaRepository<FavouritesWomb, Integer> {
}
