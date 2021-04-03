package net.juanxxiii.womb.services;

import lombok.extern.java.Log;
import net.juanxxiii.womb.database.entities.*;
import net.juanxxiii.womb.database.repositories.*;
import net.juanxxiii.womb.dto.UserLoginDto;
import net.juanxxiii.womb.exceptions.PasswordMalFormedException;
import net.juanxxiii.womb.exceptions.ResourceNotFoundException;
import net.juanxxiii.womb.security.Encrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private final FavouritesRepository favouritesRepository;
    private final FavouritesWombRepository favouritesWombRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QueryService(CountriesRepository countriesRepository,
                        UsersRepository usersRepository,
                        CategoriesRepository categoriesRepository,
                        ProductsRepository productsRepository,
                        BrandRepository brandRepository,
                        WombRepository wombRepository,
                        CommentaryRepository commentaryRepository,
                        FavouritesRepository favouritesRepository,
                        FavouritesWombRepository favouritesWombRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.countriesRepository = countriesRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
        this.brandRepository = brandRepository;
        this.wombRepository = wombRepository;
        this.commentaryRepository = commentaryRepository;
        this.favouritesRepository = favouritesRepository;
        this.favouritesWombRepository = favouritesWombRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    public int updateUsers(Users newuser, int id) {
        return usersRepository.findById(id).map(user -> {
            if (newuser.getCountry() != null) {
                Countries country = countriesRepository.findById(newuser.getCountry().getId()).orElse(null);
                newuser.setCountry(country);
            }
            return usersRepository.updateUser(newuser.getEmail(), newuser.getLastname(), newuser.getName(), newuser.getPassword(), newuser.getUsername(), newuser.getId());
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
        List<FavouritesWomb> favouritesWombs = null;
        if (!newWomb.getFavouritesWomb().isEmpty()) {
            favouritesWombs = newWomb.getFavouritesWomb();
            newWomb.setFavouritesWomb(null);
        }
        if (newWomb.getProduct() != null) {
            Products products = productsRepository.findById(newWomb.getProduct().getId()).orElse(saveProduct(newWomb.getProduct()));
            newWomb.setProduct(products);
        }
        checkWombUserForeignConstraint(newWomb);
        Womb womb = wombRepository.save(newWomb);
        if (favouritesWombs != null) {
            favouritesWombs.forEach(fav -> {
                fav.setId(womb.getId());
                favouritesWombRepository.save(fav);
            });
        }
        womb.setFavouritesWomb(favouritesWombs);
        return womb;
    }

    private void checkWombUserForeignConstraint(Womb newWomb) {
        if (newWomb.getUser() != null) {
            Users user = null;
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
            List<FavouritesWomb> favouritesWombs = womb.getFavouritesWomb();
            newWomb.getFavouritesWomb().forEach(fav -> {
                if (!favouritesWombs.contains(fav)) {
                    favouritesWombRepository.save(fav);
                }
            });
            favouritesWombs.forEach(fav -> {
                if (!newWomb.getFavouritesWomb().contains(fav)) {
                    favouritesWombRepository.deleteById(fav.getId());
                }
            });
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

    public List<Favourites> getFavouritesList() {
        return favouritesRepository.findAll();
    }

    public Favourites getFavourite(int id) throws ResourceNotFoundException {
        return favouritesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void deleteFavourite(int id) {
        favouritesRepository
                .delete(Objects
                        .requireNonNull(favouritesRepository
                                .findById(id)
                                .orElse(null)));
    }

    public Favourites saveFavourite(Favourites newFavourite) {
        List<FavouritesWomb> favouritesWombs = null;
        checkConstraintForeignKey(newFavourite);
        if (!newFavourite.getFavouritesWombs().isEmpty()) {
            favouritesWombs = newFavourite.getFavouritesWombs();
            newFavourite.setFavouritesWombs(null);
        }
        Favourites favourites = favouritesRepository.save(newFavourite);

        if (favouritesWombs != null) {
            favouritesWombs.forEach(fav -> {
                fav.setId(favourites.getId());
                favouritesWombRepository.save(fav);
            });
        }
        favourites.setFavouritesWombs(favouritesWombs);
        return favourites;
    }

    private void checkConstraintForeignKey(Favourites newFavourite) {
        if (newFavourite.getUser() != null) {
            Users users = null;
            try {
                users = usersRepository.findById(newFavourite.getUser().getId()).orElse(saveUser(newFavourite.getUser()));
            } catch (PasswordMalFormedException e) {
                log.warning("error decoding password");
            }
            newFavourite.setUser(users);
        }
    }

    public boolean checkUserExist(UserLoginDto userLoginDto) throws PasswordMalFormedException {
        Users users = usersRepository.findByUsername(userLoginDto.getUsername());
        if (users != null && users.getPassword().equals(Encrypter.encryptPassword(userLoginDto.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findUser(String username) {
        Users user = usersRepository.findByUsername(username);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findEmail(String email) {
        Users user = usersRepository.findByEmail(email);
        if (user != null) {
            return true;
        } else {
            return false;
        }
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
        Users user =usersRepository.findById(idUser).orElse(null);
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
}
