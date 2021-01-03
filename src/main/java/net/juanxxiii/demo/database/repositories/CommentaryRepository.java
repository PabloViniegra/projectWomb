package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {
}
