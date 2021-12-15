package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    List<Country> saveCountries(List<Country> countryRegions);

    Country saveCountry(Country countryRegion);

    Page<Country> getCountries(Pageable pageable);

    Country getCountryById(Integer id);

}
