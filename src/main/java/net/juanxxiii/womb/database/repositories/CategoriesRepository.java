package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    @Query(value = "SELECT * FROM categories where name=:name",nativeQuery = true)
    Categories findCategoryByName(@Param("name") String name);
}
