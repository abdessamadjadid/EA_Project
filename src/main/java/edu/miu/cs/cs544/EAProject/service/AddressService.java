package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.Address;

public interface AddressService {

    Address getAddressId(Integer id);

    Address saveAddress(Address course);

    Address updateAddress(Address course);

}
