package net.juanxxiii.demo.database.repositories;

import net.juanxxiii.demo.database.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "SELECT * from brand where name=:name", nativeQuery = true)
    Brand getBrandByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE Brand b SET b.name=:name where b.id=:id")
    int updateBrand(@Param("name") String name, @Param("id") int id);

    @Query("Select max(s.id) from Brand s")
    int lastID();
}
