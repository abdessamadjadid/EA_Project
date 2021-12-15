package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.Country;
import edu.miu.cs.cs544.EAProject.repository.CountryRepository;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public List<Country> saveCountries(List<Country> countryRegions) {
        return repository.saveAll(countryRegions);
    }

    @Override
    public Country saveCountry(Country countryRegion) {
        return repository.save(countryRegion);
    }

    @Override
    public Page<Country> getCountries(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Country getCountryById(Integer id) {
        return repository.getById(id.toString());
    }
}
