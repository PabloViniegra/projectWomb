package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Integer> {

    @Query("SELECT MAX(f.id) FROM Favourites f")
    int lastId();
}
