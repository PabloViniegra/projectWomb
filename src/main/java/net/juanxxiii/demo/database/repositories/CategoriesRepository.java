package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {

    @Query(value = "SELECT * FROM categories where name=:name",nativeQuery = true)
    Categories findCategoryByName(@Param("name") String name);
}
