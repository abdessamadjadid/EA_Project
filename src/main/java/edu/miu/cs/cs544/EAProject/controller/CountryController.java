package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(params = {"page"})
    public Page<CountryRegion> findAll(Pageable pageable) {
        return countryService.getCountries(pageable);
    }

    @GetMapping("/{id}")
    public CountryRegion findById(@PathVariable(name = "id") Integer id) {
        return countryService.getCountryById(id);
    }

    @PostMapping
    public CountryRegion saveCountry(@RequestBody CountryRegion country) {
        return countryService.saveCountry(country);
    }

    public List<CountryRegion> saveCountries(List<CountryRegion> countries) {
        return countryService.saveCountries(countries);
    }

}
