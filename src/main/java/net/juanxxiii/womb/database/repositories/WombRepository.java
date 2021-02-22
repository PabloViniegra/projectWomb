package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Womb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WombRepository extends JpaRepository<Womb, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE womb SET iduser=:idUser WHERE idwomb=:id", nativeQuery = true)
    int updateUser(@Param("idUser") int idUser, @Param("id") int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE womb SET idproduct=:idProduct WHERE idwomb=:id",nativeQuery = true)
    int updateProducts(@Param("idProduct") int idProduct, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Womb w SET w.date=:date, w.review=:review,w.score=:score WHERE w.id=:id")
    int updateWomb(@Param("date") String date, @Param("review") String review, @Param("score") float score, @Param("id") int id);

    @Query("SELECT MAX(w.id) FROM Womb w")
    int lastId();
}
