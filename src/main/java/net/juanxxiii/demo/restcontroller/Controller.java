package net.juanxxiii.demo.restcontroller;

import net.juanxxiii.demo.database.entities.Countries;
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
}
