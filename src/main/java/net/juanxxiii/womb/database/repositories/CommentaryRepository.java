package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Commentary;
import net.juanxxiii.womb.database.entities.Womb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Integer> {

    List<Commentary> findByWomb(Womb womb);
}
