package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.UserLoginSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginSystem,Integer> {
    UserLoginSystem findByUsername(String username);
    @Transactional
    @Modifying
    @Query("UPDATE UserLoginSystem u SET u.username=:username, u.password = :password WHERE u.id = :id")
    int updateUserSystem(@Param("username") String username,@Param("password") String password, @Param("id") int id);
}
