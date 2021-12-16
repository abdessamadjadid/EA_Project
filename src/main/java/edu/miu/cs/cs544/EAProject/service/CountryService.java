package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;

public interface CountryService {

    CountryRegion getCountryId(Integer id);

    CountryRegion saveCountry(CountryRegion country);

    CountryRegion updateCountry(CountryRegion country);

}
