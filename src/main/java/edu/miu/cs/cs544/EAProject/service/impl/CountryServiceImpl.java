package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import edu.miu.cs.cs544.EAProject.service.CountryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public List<CountryRegion> saveCountries(List<CountryRegion> countryRegions) {
        return null;
    }

    @Override
    public CountryRegion saveCountry(CountryRegion countryRegion) {
        return null;
    }

    @Override
    public Page<CountryRegion> getCountries(Pageable pageable) {
        return null;
    }

    @Override
    public CountryRegion getCountryById(Integer id) {
        return null;
    }
}
