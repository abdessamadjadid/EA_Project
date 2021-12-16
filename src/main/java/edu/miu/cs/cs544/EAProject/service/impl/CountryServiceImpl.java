package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import edu.miu.cs.cs544.EAProject.repository.CountryRepository;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository repository;

    @Override
    public CountryRegion getCountryId(Integer id) {
        return repository.getById(id);
    }

    @Override
    public CountryRegion saveCountry(CountryRegion country) {
        return repository.save(country);
    }

    @Override
    public CountryRegion updateCountry(CountryRegion country) {
        return repository.save(country);
    }
}
