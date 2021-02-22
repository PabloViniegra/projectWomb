package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
}
