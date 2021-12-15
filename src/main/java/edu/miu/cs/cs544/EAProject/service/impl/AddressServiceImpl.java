package edu.miu.cs.cs544.EAProject.service.impl;

import edu.miu.cs.cs544.EAProject.domain.Address;
import edu.miu.cs.cs544.EAProject.repository.AddressRepository;
import edu.miu.cs.cs544.EAProject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public Address getAddressId(Integer id) {
        return repository.getById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return repository.save(address);
    }

    @Override
    public Address updateAddress(Address address) {
        return repository.save(address);
    }
}
