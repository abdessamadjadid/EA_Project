package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import edu.miu.cs.cs544.EAProject.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryRepository repository;

    @GetMapping(params = {"page"})
    public Page<CountryRegion> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public CountryRegion findById(@PathVariable(name = "id") String id) {
        return repository.findById(id).orElse(new CountryRegion());
    }

    @PostMapping
    public CountryRegion saveCountry(@RequestBody CountryRegion country) {
        return repository.save(country);
    }

    public List<CountryRegion> saveCountries(List<CountryRegion> countries) {
        return repository.saveAll(countries);
    }

}
