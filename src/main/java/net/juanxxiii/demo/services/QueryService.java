package net.juanxxiii.demo.services;

import lombok.extern.java.Log;
import net.juanxxiii.demo.database.entities.Countries;
import net.juanxxiii.demo.database.repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class QueryService {

    private final CountriesRepository countriesRepository;

    @Autowired
    public QueryService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public List<Countries> getCountriesList() {
        return countriesRepository.findAll();
    }

    public Countries getCountry(int id) {
        return countriesRepository.findById(id).orElse(null);
    }
}
