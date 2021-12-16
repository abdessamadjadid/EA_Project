package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityListeners;
import javax.validation.Valid;

@RestController
@RequestMapping("/countries")
@EntityListeners(AuditListener.class)
public class CountryController {

    @Autowired
    private CountryService service;

    @GetMapping("/{id}")
    public CountryRegion findById(@Valid @PathVariable(name = "id") Integer id) {
        return service.getCountryId(id);
    }

    @PostMapping
    public CountryRegion saveCountry(@RequestBody CountryRegion country) {
        return service.saveCountry(country);
    }

    @PutMapping
    public CountryRegion updateCountry(@Valid @RequestBody CountryRegion country) {
        return service.updateCountry(country);
    }

}
