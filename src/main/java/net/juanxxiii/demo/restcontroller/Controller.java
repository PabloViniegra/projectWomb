package net.juanxxiii.demo.restcontroller;

import net.juanxxiii.demo.database.entities.*;
import net.juanxxiii.demo.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/womb/api")
public class Controller {
    private final QueryService queryService;

    @Autowired
    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    //Countries Mapping
    @GetMapping("/countries")
    public ResponseEntity<List<Countries>> getCountriesList() {
        return ResponseEntity.ok(queryService.getCountriesList());
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<?> getCountry(@PathVariable("id") int id) {
        Countries country = queryService.getCountry(id);
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
        Users user = queryService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody Users newuser) {
        Users user = queryService.saveUser(newuser);
        if (user != null) {
            return ResponseEntity.ok(newuser);
        } else {
            return ResponseEntity.notFound().build();
        }
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
        Categories category = queryService.getCategory(id);
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
        Products product = queryService.getProduct(id);
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
        Brand brand = queryService.getBrand(id);
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

    @GetMapping("womb/{id}")
    public ResponseEntity<?> getWomb(@PathVariable("id") int id) {
        Womb womb = queryService.getWomb(id);
        if (womb != null) {
            return ResponseEntity.ok(womb);
        } else {
            return ResponseEntity.notFound().build();
        }
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

}
