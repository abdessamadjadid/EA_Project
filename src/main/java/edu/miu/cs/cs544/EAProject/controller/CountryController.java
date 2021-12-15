package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.Country;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(params = {"page"})
    public Page<Country> findAll(Pageable pageable) {
        return countryService.getCountries(pageable);
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable(name = "id") Integer id) {
        return countryService.getCountryById(id);
    }

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    public List<Country> saveCountries(List<Country> countries) {
        return countryService.saveCountries(countries);
    }

}
