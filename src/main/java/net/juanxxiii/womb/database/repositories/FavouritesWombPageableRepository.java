package net.juanxxiii.womb.database.repositories;

import net.juanxxiii.womb.database.entities.FavouritesWomb;
import net.juanxxiii.womb.database.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FavouritesWombPageableRepository extends PagingAndSortingRepository<FavouritesWomb, Integer> {

    Page<FavouritesWomb> findFavouritesWombByUser(Users user, Pageable pageable);
}
