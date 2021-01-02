package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Womb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WombRepository extends JpaRepository<Womb, Integer> {
}
