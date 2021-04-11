package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.Countries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountriesPageableRepository extends PagingAndSortingRepository<Countries, Integer> {

    Page<Countries> findAll(Pageable pageable);
}
