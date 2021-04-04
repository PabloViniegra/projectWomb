package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.FavouritesWomb;
import net.juanxxiii.womb.database.entities.Users;
import net.juanxxiii.womb.database.entities.Womb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouritesWombRepository extends JpaRepository<FavouritesWomb, Integer> {

    List<FavouritesWomb> findAllByUser(Users user);




}
