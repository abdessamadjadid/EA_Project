package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    List<CountryRegion> saveCountries(List<CountryRegion> countryRegions);

    CountryRegion saveCountry(CountryRegion countryRegion);

    Page<CountryRegion> getCountries(Pageable pageable);

    CountryRegion getCountryById(Integer id);

}
