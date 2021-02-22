package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.email=:email, u.lastname=:lastname, u.name=:name, u.password=:password,u.username=:username WHERE u.id = :id")
    int updateUser(@Param("email") String email, @Param("lastname") String lastname, @Param("name") String name, @Param("password") String password,@Param("username") String username, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.name = :name WHERE u.id = :id")
    int updateUserName(@Param("name") String name, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.lastname = :lastname WHERE u.id = :id")
    int updateUserLastname(@Param("lastname") String lastname, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.email = :email WHERE u.id = :id")
    int updateUserEmail(@Param("email") String email, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.username = :username WHERE u.id = :id")
    int updateUserUsername(@Param("username") String username, @Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.password = :password WHERE u.id = :id")
    int updateUserPassword(@Param("password") String password, @Param("id") int id);

    Users findByUsernameAndPassword(String username, String password);
}
