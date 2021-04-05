package net.juanxxiii.womb.restcontroller;

import lombok.extern.java.Log;
import net.juanxxiii.womb.common.utils.Copy;
import net.juanxxiii.womb.database.entities.*;
import net.juanxxiii.womb.dto.UserLoginDto;
import net.juanxxiii.womb.exceptions.PasswordMalFormedException;
import net.juanxxiii.womb.exceptions.ResourceNotFoundException;
import net.juanxxiii.womb.security.Encrypter;
import net.juanxxiii.womb.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Log
@CrossOrigin
@RestController
@RequestMapping("/womb/api")
public class Controller {
    private final QueryService queryService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public Controller(QueryService queryService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.queryService = queryService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //Countries Mapping
    @GetMapping("/countries")
    public ResponseEntity<List<Countries>> getCountriesList() {
        return ResponseEntity.ok(queryService.getCountriesList());
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<?> getCountry(@PathVariable("id") int id) {
        Countries country = null;
        try {
            country = queryService.getCountry(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("country doesn't exist");
        }
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/countries/name/{name}")
    public ResponseEntity<?> getCountryByName(@PathVariable("name") String name) {
        Countries country = queryService.getCountryByName(name);
        if (country != null) {
            return ResponseEntity.ok(country);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Users Mapping
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsersList() {
        return ResponseEntity.ok(queryService.getUsersList());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        Users user = null;
        try {
            user = queryService.getUser(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("The user doesn't exist");
        }
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody Users newuser) {
        Users user = null;
        try {
            user = queryService.saveUser(newuser);
        } catch (PasswordMalFormedException e) {
            System.out.println("can't set the password. Error encoding");
        }
        if (user != null) {
            return ResponseEntity.ok(newuser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        boolean checkUser = false;
        try {
            checkUser = queryService.checkUserExist(userLoginDto);
        } catch (PasswordMalFormedException e) {
            System.out.println("La contraseña no es la misma");
        }
        if (checkUser) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/find/user/{username}")
    public ResponseEntity<?> findAnUsername(@PathVariable("username") String username) {
        boolean check = false;
        check = queryService.findUser(username);
        if (check) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<?> findEmail(@PathVariable("email") String email) {
        boolean check = false;
        check = queryService.findEmail(email);
        if (check) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(queryService.getUserByUsername(username));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUsers(@RequestBody Users newuser, @PathVariable("id") int id) {
        int user = queryService.updateUsers(newuser, id);
        if (user != -1) {
            return ResponseEntity.ok("User updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<?> partialUpdateUser(@PathVariable("id") int id, @RequestBody Users newUser) {
        Users user = null;

        try {
            user = queryService.getUser(id);
            Copy.copyNonNullProperties(newUser, user);
            user.setId(id);
            user.setPassword(Encrypter.encryptPassword(user.getPassword()));
            queryService.updateUsers(user, user.getId());
        } catch (ResourceNotFoundException | PasswordMalFormedException e) {
            System.out.println("The user doesn't exist");
        }
        return ResponseEntity.ok(Objects.requireNonNull(user));
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable("id") int id) {
        queryService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    //Categories Mapping
    @GetMapping("/categories")
    public ResponseEntity<List<Categories>> getCategoriesList() {
        return ResponseEntity.ok(queryService.getCategoriesList());
    }

    @GetMapping("/categories/name/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(queryService.getCategoryByName(name));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") int id) {
        Categories category = null;
        try {
            category = queryService.getCategory(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("category doesn't exist");
        }
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<?> newCategory(@RequestBody Categories newcategory) {
        Categories category = queryService.saveCategory(newcategory);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategories(@PathVariable("id") int id) {
        queryService.deleteCategory(id);
        return ResponseEntity.ok("category deleted");
    }

    //Products Mapping
    @GetMapping("/products")
    public ResponseEntity<List<Products>> getProductsList() {
        return ResponseEntity.ok(queryService.getProductsList());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id) {
        Products product = null;
        try {
            product = queryService.getProduct(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("product doen't exist");
        }
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> newProduct(@RequestBody Products newproduct) {
        Products product = queryService.saveProduct(newproduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Products product, @PathVariable("id") int id) {
        int productrequest = queryService.updateProducts(product, id);
        if (productrequest != -1) {
            return ResponseEntity.ok("product updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
        queryService.deleteProduct(id);
        return ResponseEntity.ok("product deleted");
    }

    //Brand Mapping
    @GetMapping("/brand")
    public ResponseEntity<List<Brand>> getBrandList() {
        return ResponseEntity.ok(queryService.getBrandList());
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable("id") int id) {
        Brand brand = null;
        try {
            brand = queryService.getBrand(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("brand doesn't exist");
        }
        if (brand != null) {
            return ResponseEntity.ok(brand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/brand/name/{name}")
    public ResponseEntity<?> getBrandByName(@PathVariable("name") String name) {
        Brand brand = queryService.getBrandByName(name);
        if (brand != null) {
            return ResponseEntity.ok(brand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/brand")
    public ResponseEntity<?> newBrand(@RequestBody Brand newBrand) {
        Brand brand = queryService.saveBrand(newBrand);
        if (brand != null) {
            return ResponseEntity.ok(brand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") int id) {
        queryService.deleteBrand(id);
        return ResponseEntity.ok("brand deleted");
    }

    //Womb Mapping
    @GetMapping("/womb")
    public ResponseEntity<List<Womb>> getWombList() {
        return ResponseEntity.ok(queryService.getWombList());
    }

    @GetMapping("/womb/user/{user}")
    public ResponseEntity<List<Womb>> getWombsByUser (@PathVariable("user") int idUser) {
        return ResponseEntity.ok(queryService.getWombByUser(idUser));
    }

    @GetMapping("/lastwombs")
    public ResponseEntity<List<Womb>> getLastWombs() {
        return ResponseEntity.ok(queryService.getLastWombsList());
    }

    @GetMapping("womb/{id}")
    public ResponseEntity<?> getWomb(@PathVariable("id") int id) {
        Womb womb = null;
        try {
            womb = queryService.getWomb(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("womb doesn't exist");
        }
        if (womb != null) {
            return ResponseEntity.ok(womb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/womb/search/{keyword}")
    public ResponseEntity<List<Womb>> findWombsWhichContainsKeyword(@PathVariable("keyword") String keyword) {
        return ResponseEntity.ok(queryService.searchWombsContainsKeyword(keyword));
    }

    @PostMapping("/womb")
    public ResponseEntity<?> newWomb(@RequestBody Womb newwomb) {
        Womb womb = queryService.saveWomb(newwomb);
        if (womb != null) {
            return ResponseEntity.ok(womb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/womb/{id}")
    public ResponseEntity<?> updateWomb(@RequestBody Womb newWomb, @PathVariable("id") int id) {
        int request = queryService.updateWomb(newWomb, id);
        if (request != -1) {
            return ResponseEntity.ok("Womb order updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/womb/{id}")
    public ResponseEntity<?> deleteWomb(@PathVariable("id") int id) {
        queryService.deleteWomb(id);
        return ResponseEntity.ok("womb deleted");
    }

    //Commentary Mapping
    @GetMapping("/commentaries")
    public ResponseEntity<List<Commentary>> getCommentariesList() {
        return ResponseEntity.ok(queryService.getCommentariesList());
    }

    @GetMapping("/commentaries/{id}")
    public ResponseEntity<?> getCommentary(@PathVariable("id") int id) {
        Commentary commentary = null;
        try {
            commentary = queryService.getCommentary(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("Commentary doesn't exist");
        }
        if (commentary != null) {
            return ResponseEntity.ok(commentary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/commentaries/womb/{womb}")
    public ResponseEntity<List<Commentary>> getCopmmentariesByWomb(@PathVariable("womb") int idWomb) {
        return ResponseEntity.ok(queryService.getCommentariesByWomb(idWomb));
    }

    @PostMapping("/commentaries")
    public ResponseEntity<?> newCommentary(@RequestBody Commentary newcommentary) {
        Commentary commentary = queryService.saveCommentary(newcommentary);
        if (commentary != null) {
            return ResponseEntity.ok(commentary);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/commentaries/{id}")
    public ResponseEntity<?> deleteCommentary(@PathVariable("id") int id) {
        queryService.deleteCommentary(id);
        return ResponseEntity.ok("commentary deleted");
    }

    //Favourites Mapping
    @GetMapping("/favourites")
    public ResponseEntity<List<FavouritesWomb>> getFavouritesList() {
        return ResponseEntity.ok(queryService.getFavouritesList());
    }

    @GetMapping("/favourites/user/{username}")
    public ResponseEntity<List<FavouritesWomb>> getFavouritesWombByUsername(@PathVariable("username") String username) {
        List<FavouritesWomb> favs = queryService.getFavouritesWombByUsername(username);
        if (favs != null) {
            return ResponseEntity.ok(favs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/favourites/check/{username}/{idwomb}")
    public ResponseEntity<?> checkFavouriteUserExists(@PathVariable("username") String username, @PathVariable("idwomb") int idwomb) {
        boolean checkFavExists = queryService.checkFavouriteUserExists(username,idwomb);
        if (checkFavExists) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/favourites/{username}/{idwomb}")
    public ResponseEntity<FavouritesWomb> getWombByUserAndWomb(@PathVariable("username") String username, @PathVariable("idwomb") int idwomb) {
        FavouritesWomb favouritesWomb = queryService.getWombByUserAndWomb(username,idwomb);
        if (favouritesWomb != null) {
            return ResponseEntity.ok(favouritesWomb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/favourites/{id}")
    public ResponseEntity<?> getFavourite(@PathVariable("id") int id) {
        FavouritesWomb favourites = null;
        try {
            favourites = queryService.getFavourite(id);
        } catch (ResourceNotFoundException e) {
            System.out.println("Favourite doesn't exist");
        }
        if (favourites != null) {
            return ResponseEntity.ok(favourites);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/favourites")
    public ResponseEntity<?> newFavourite(@RequestBody FavouritesWomb newFavourite) {
        FavouritesWomb favourites = queryService.saveFavourite(newFavourite);
        if (favourites != null) {
            return ResponseEntity.ok(favourites);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/favourites/{id}")
    public ResponseEntity<?> deleteFavourite(@PathVariable("id") int id) {
        queryService.deleteFavourite(id);
        return ResponseEntity.ok("favourite deleted");
    }



}
