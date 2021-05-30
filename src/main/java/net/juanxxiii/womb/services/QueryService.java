package net.juanxxiii.womb.services;

import lombok.extern.java.Log;
import net.juanxxiii.womb.database.entities.*;
import net.juanxxiii.womb.database.repositories.*;
import net.juanxxiii.womb.dto.UserLoginDto;
import net.juanxxiii.womb.exceptions.PasswordMalFormedException;
import net.juanxxiii.womb.exceptions.ResourceNotFoundException;
import net.juanxxiii.womb.security.Encrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log
public class QueryService {

    private final CountriesRepository countriesRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;
    private final BrandRepository brandRepository;
    private final WombRepository wombRepository;
    private final CommentaryRepository commentaryRepository;
    private final FavouritesWombRepository favouritesWombRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CountriesPageableRepository countriesPageableRepository;
    private final WombPageableRepository wombPageableRepository;
    private final FavouritesWombPageableRepository favouritesWombPageableRepository;

    @Autowired
    public QueryService(CountriesRepository countriesRepository,
                        UsersRepository usersRepository,
                        CategoriesRepository categoriesRepository,
                        ProductsRepository productsRepository,
                        BrandRepository brandRepository,
                        WombRepository wombRepository,
                        CommentaryRepository commentaryRepository,
                        FavouritesWombRepository favouritesWombRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder,
                        CountriesPageableRepository countriesPageableRepository,
                        WombPageableRepository wombPageableRepository,
                        FavouritesWombPageableRepository favouritesWombPageableRepository) {
        this.countriesRepository = countriesRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
        this.brandRepository = brandRepository;
        this.wombRepository = wombRepository;
        this.commentaryRepository = commentaryRepository;
        this.favouritesWombRepository = favouritesWombRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.countriesPageableRepository = countriesPageableRepository;
        this.wombPageableRepository = wombPageableRepository;
        this.favouritesWombPageableRepository = favouritesWombPageableRepository;
    }


    public List<Countries> getCountriesList() {
        return countriesRepository.findAll();
    }

    public Countries getCountry(int id) throws ResourceNotFoundException {
        return countriesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Countries getCountryByName(String name) {
        return countriesRepository.findByName(name);
    }

    public List<Users> getUsersList() {
        return usersRepository.findAll();
    }

    public Users getUser(int id) throws ResourceNotFoundException {
        return usersRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Users saveUser(Users newuser) throws PasswordMalFormedException {
        if (newuser.getCountry() != null) {
            Countries country = countriesRepository.findById(newuser.getCountry().getId()).orElse(null);
            newuser.setCountry(country);
        }
        newuser.setPassword(Encrypter.encryptPassword(newuser.getPassword()));
        return usersRepository.save(newuser);
    }

    public int updateUsers(Users newUser, int id) {
        return usersRepository.findById(id).map(user -> {
            if (newUser.getCountry() != null) {
                Countries country = countriesRepository.findById(newUser.getCountry().getId()).orElse(null);
                newUser.setCountry(country);
            }
            try {
                if (!newUser.getPassword().equals(user.getPassword())) {

                    newUser.setPassword(Encrypter.encryptPassword(newUser.getPassword()));
                }
            } catch (PasswordMalFormedException e) {
                e.printStackTrace();
            }
            return usersRepository.updateUser(newUser.getEmail(), newUser.getLastname(), newUser.getName(), newUser.getPassword(), newUser.getUsername(), newUser.getId(), newUser.getCountry().getId());
        }).orElse(-1);
    }

    public void deleteUser(int id) {
        usersRepository
                .delete(Objects
                        .requireNonNull(usersRepository
                                .findById(id)
                                .orElse(null)));
    }


    public List<Categories> getCategoriesList() {
        return categoriesRepository.findAll();
    }

    public Categories getCategory(int id) throws ResourceNotFoundException {
        return categoriesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Categories getCategoryByName(String name) {
        return categoriesRepository.findCategoryByName(name);
    }

    public Categories saveCategory(Categories newcategory) {
        return categoriesRepository.save(newcategory);
    }

    public void deleteCategory(int id) {
        categoriesRepository
                .delete(Objects
                        .requireNonNull(categoriesRepository
                                .findById(id)
                                .orElse(null)));
    }

    public List<Products> getProductsList() {
        return productsRepository.findAll();
    }

    public Products getProduct(int id) throws ResourceNotFoundException {
        return productsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Products saveProduct(Products newproduct) {
        if (newproduct.getCategory() != null) {
            Categories category = categoriesRepository.findById(newproduct.getCategory().getId()).orElse(categoriesRepository.save(newproduct.getCategory()));
            newproduct.setCategory(category);
        }
        if (newproduct.getBrand() != null) {
            Brand brand = brandRepository.findById(newproduct.getBrand().getId()).orElse(brandRepository.save(newproduct.getBrand()));
            newproduct.setBrand(brand);
        }
        return productsRepository.save(newproduct);
    }

    public void deleteProduct(int id) {
        productsRepository
                .delete(Objects
                        .requireNonNull(productsRepository
                                .findById(id)
                                .orElse(null)));
    }

    public int updateProducts(Products product, int id) {
        return productsRepository.findById(id).map(prod -> {
            if (product.getCategory() != null) {
                Categories categories = categoriesRepository.findById(product.getCategory().getId()).orElse(null);
                product.setCategory(categories);
            }
            if (product.getBrand() != null) {
                Brand brand = brandRepository.findById(product.getBrand().getId()).orElse(null);
                product.setBrand(brand);
            }
            return productsRepository.updateProduct(product.getName(), product.getImage(), product.getId());
        }).orElse(-1);
    }

    public List<Brand> getBrandList() {
        return brandRepository.findAll();
    }

    public Brand getBrand(int id) throws ResourceNotFoundException {
        return brandRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Brand getBrandByName(String name) {
        return brandRepository.getBrandByName(name);
    }

    public Brand saveBrand(Brand newbrand) {
        return brandRepository.save(newbrand);
    }

    public void deleteBrand(int id) {
        brandRepository
                .delete(Objects
                        .requireNonNull(brandRepository
                                .findById(id)
                                .orElse(null)));
    }


    public List<Womb> getWombList() {
        return wombRepository.findAll();
    }

    public Womb getWomb(int id) throws ResourceNotFoundException {
        return wombRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Womb saveWomb(Womb newWomb) {

        if (newWomb.getProduct() != null) {
            Products products = productsRepository.findById(newWomb.getProduct().getId()).orElse(saveProduct(newWomb.getProduct()));
            newWomb.setProduct(products);
        }
        checkWombUserForeignConstraint(newWomb);
        return wombRepository.save(newWomb);
    }

    private void checkWombUserForeignConstraint(Womb newWomb) {
        if (newWomb.getUser() != null) {
            Users user;
            System.out.println(newWomb.getUser().getId());
            user = usersRepository.findByUsername(newWomb.getUser().getUsername());
            if (user == null) {
                try {
                    saveUser(newWomb.getUser());
                } catch (PasswordMalFormedException e) {
                    e.printStackTrace();
                }
            }
            newWomb.setUser(user);
        }
    }

    public int updateWomb(Womb newWomb, int id) {

        return wombRepository.findById(id).map(womb -> {
            if (!newWomb.getUser().equals(womb.getUser())) {
                Users user = null;
                try {
                    user = usersRepository.findById(newWomb.getUser().getId()).orElse(saveUser(newWomb.getUser()));
                } catch (PasswordMalFormedException e) {
                    log.warning("error decoding password");
                }
                assert user != null;
                wombRepository.updateUser(user.getId(), id);
            }
            if (!newWomb.getProduct().equals(womb.getProduct())) {
                Products products = productsRepository.findById(newWomb.getProduct().getId()).orElse(saveProduct(newWomb.getProduct()));
                wombRepository.updateProducts(products.getId(), id);
            }
            return wombRepository.updateWomb(newWomb.getDate(), newWomb.getReview(), newWomb.getScore(), id);
        }).orElse(-1);
    }

    public void deleteWomb(int id) {
        wombRepository
                .delete(Objects
                        .requireNonNull(wombRepository
                                .findById(id)
                                .orElse(null)));
    }

    public List<Commentary> getCommentariesList() {
        return commentaryRepository.findAll();
    }

    public Commentary getCommentary(int id) throws ResourceNotFoundException {
        return commentaryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Commentary saveCommentary(Commentary newcommentary) {
        if (newcommentary.getWomb() != null) {
            Womb womb = wombRepository.findById(newcommentary.getWomb().getId()).orElse(saveWomb(newcommentary.getWomb()));
            newcommentary.setWomb(womb);
        }
        if (newcommentary.getUser() != null) {
            Users user = usersRepository.findByUsername(newcommentary.getUser().getUsername());
            if (user != null) {
                newcommentary.setUser(user);
            }
        }
        return commentaryRepository.save(newcommentary);
    }

    public void deleteCommentary(int id) {
        commentaryRepository
                .delete(Objects
                        .requireNonNull(commentaryRepository
                                .findById(id)
                                .orElse(null)));
    }

    public List<FavouritesWomb> getFavouritesList() {
        return favouritesWombRepository.findAll();
    }

    public FavouritesWomb getFavourite(int id) throws ResourceNotFoundException {
        return favouritesWombRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteFavourite(int id) {
        favouritesWombRepository
                .delete(Objects
                        .requireNonNull(favouritesWombRepository
                                .findById(id)
                                .orElse(null)));
    }

    public FavouritesWomb saveFavourite(FavouritesWomb newFavourite) {
        Users user = checkConstraintForeignKey(newFavourite);
        Womb womb = null;
        FavouritesWomb favouritesWomb = null;
        if (newFavourite.getWomb() != null) {
            womb = wombRepository.findById(newFavourite.getWomb().getId()).orElse(null);
        }

        if (user != null && womb != null) {
            favouritesWomb = favouritesWombRepository.save(newFavourite);
        }
        return favouritesWomb;
    }

    private Users checkConstraintForeignKey(FavouritesWomb newFavourite) {
        Users users = null;
        if (newFavourite.getUser() != null) {
            try {
                users = usersRepository.findById(newFavourite.getUser().getId()).orElseThrow(ResourceNotFoundException::new);
            } catch (ResourceNotFoundException e) {
                log.warning("user doesn't exist");
            }
        }
        return users;
    }

    public boolean checkUserExist(UserLoginDto userLoginDto) throws PasswordMalFormedException {
        Users users = usersRepository.findByUsername(userLoginDto.getUsername());
        return users != null && users.getPassword().equals(Encrypter.encryptPassword(userLoginDto.getPassword()));
    }

    public boolean findUser(String username) {
        Users user = usersRepository.findByUsername(username);
        return user != null;
    }

    public boolean findEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        return user != null;
    }

    public List<Womb> getLastWombsList() {
        return wombRepository.findFirst10ByOrderByIdDesc();
    }

    public Users getUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            log.warning("User doesn't exist");
        }
        return user;
    }

    public List<Womb> getWombByUser(int idUser) {
        List<Womb> wombs = new ArrayList<>();
        Users user = usersRepository.findById(idUser).orElse(null);
        if (user != null) {
            wombs = wombRepository.findByUser(user);
        } else {
            log.info("User doesn't exist");
        }
        return wombs;
    }

    public List<Commentary> getCommentariesByWomb(int idWomb) {
        List<Commentary> commentaries = new ArrayList<>();
        Womb womb = wombRepository.findById(idWomb).orElse(null);
        if (womb != null) {
            commentaries = commentaryRepository.findByWombOrderByIdDesc(womb);
        } else {
            log.info("Womb doesn't exist");
        }
        return commentaries;
    }

    public List<Womb> searchWombsContainsKeyword(String keyword) {
        return wombRepository.searchWomb(keyword);
    }


    public List<FavouritesWomb> getFavouritesWombsList() {
        return favouritesWombRepository.findAll();
    }

    public FavouritesWomb getFavouriteWombId(int id) {
        return favouritesWombRepository.findById(id).orElse(null);
    }

    public List<FavouritesWomb> getFavouritesWombByUsername(String username) {
        List<FavouritesWomb> favs = null;
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            favs = favouritesWombRepository.findAllByUser(user);
        }
        return favs;
    }

    public List<FavouritesWomb> getFavouritesWombByUsernamePageable(Integer pageNo, Integer pageSize, String sortBy, String username) throws ResourceNotFoundException {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            Page<FavouritesWomb> pagedResult = favouritesWombPageableRepository.findFavouritesWombByUser(user, paging);
            if (pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return new ArrayList<>();
            }
        } else {
            throw new ResourceNotFoundException("That user doens't exist");
        }
    }
    public boolean checkFavouriteUserExists(String username, int idwomb) {
        Users user = usersRepository.findByUsername(username);
        Womb womb;
        AtomicBoolean check = new AtomicBoolean(false);
        try {
            womb = wombRepository.findById(idwomb).orElseThrow(ResourceNotFoundException::new);

        } catch (ResourceNotFoundException e) {
            return false;
        }
        Womb finalWomb = womb;
        if (user != null && womb != null) {
            favouritesWombRepository.findAllByUser(user).forEach(fav -> {
                if (finalWomb.equals(fav.getWomb())) {
                    check.set(true);
                }
            });
        }
        return check.get();
    }

    public FavouritesWomb getWombByUserAndWomb(String username, int idwomb) {
        Users user = usersRepository.findByUsername(username);
        Womb womb = wombRepository.findById(idwomb).orElse(null);
        AtomicReference<FavouritesWomb> favouritesWomb = new AtomicReference<>(null);
        if (user != null && womb != null) {
            favouritesWombRepository.findAllByUser(user).forEach(fav -> {
                if (womb.equals(fav.getWomb())) {
                    favouritesWomb.set(fav);
                }
            });
        }
        return favouritesWomb.get();
    }

    public List<Countries> getAllCountriesPageable(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Countries> pagedResult = countriesPageableRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Womb> findWombsWhichContainsKeywordsPaginable(String keyword, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Womb> pagedResult = wombPageableRepository.searchWomb(keyword,paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }


    public int getWombNumber(String keyword) {
        return wombRepository.searchWomb(keyword).size();
    }

    public List<Womb> getWombsByUser(String username, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Womb> pagedResult = wombPageableRepository.getWombByUser(username,paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public int getWombNumberByUser(String username) throws ResourceNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            return wombRepository.findByUser(user).size();
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public int getNumberFavouritesWombByUser(String username) throws ResourceNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            return favouritesWombRepository.findAllByUser(user).size();
        } else {
            throw new ResourceNotFoundException("That user doesn't exist");
        }
    }
}
