package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Products p SET p.name=:name, p.image=:image WHERE p.id=:id")
    int updateProduct(@Param("name") String name, @Param("image") String image, @Param("id") int id);
}
