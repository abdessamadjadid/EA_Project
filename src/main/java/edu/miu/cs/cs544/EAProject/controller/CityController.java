package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.City;
import edu.miu.cs.cs544.EAProject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository repository;

    @GetMapping
    public Page<City> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable(name = "id") Integer id) {
        return repository.findById(id).orElse(new City());
    }

    @PostMapping
    public City saveCity(@RequestBody City city) {
        return repository.save(city);
    }

    private List<City> saveCities(List<City> cityList) {
        return repository.saveAll(cityList);
    }

}
