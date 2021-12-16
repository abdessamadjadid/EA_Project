package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.City;
import edu.miu.cs.cs544.EAProject.repository.CityRepository;
import edu.miu.cs.cs544.EAProject.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository repository;

    @Override
    public City saveCity(City city) {
        return repository.save(city);
    }
}
