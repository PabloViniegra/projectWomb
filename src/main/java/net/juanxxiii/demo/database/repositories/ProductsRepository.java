package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("UPDATE Products p SET p.name=:name, p.image=:image WHERE p.id=:id")
    int updateProduct(@Param("name") String name, @Param("image") String image, @Param("id") int id);
}
