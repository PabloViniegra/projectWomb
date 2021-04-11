package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Womb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface WombPageableRepository extends PagingAndSortingRepository<Womb, Integer> {

    @Query("select w from Womb w where w.review like %?1% or w.user.name like %?1% or w.user.lastname like %?1% or w.user.username like %?1% or w.user.country.name like %?1% or w.user.country.nicename like %?1% or w.product.name like %?1% or w.product.category.name like %?1% or w.product.category.description like %?1% or w.product.brand.name like %?1%")
    Page<Womb> searchWomb(String keyword, Pageable pageable);
}
