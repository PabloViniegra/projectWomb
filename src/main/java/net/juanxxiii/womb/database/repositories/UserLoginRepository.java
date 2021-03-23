package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.UserLoginSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginSystem,Integer> {
    UserLoginSystem findByUsername(String username);
}
