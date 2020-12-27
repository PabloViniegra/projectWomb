package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}
