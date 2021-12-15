package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.Address;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityListeners;
import javax.validation.Valid;

@RestController
@RequestMapping("/addresses")
@EntityListeners(AuditListener.class)
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/{id}")
    public Address findById(@Valid @PathVariable(name = "id") Integer id) {
        return service.getAddressId(id);
    }

    @PostMapping
    public Address saveAddress(@RequestBody Address address) {
        return service.saveAddress(address);
    }

    @PutMapping
    public Address updateAddress(@Valid @RequestBody Address address) {
        return service.updateAddress(address);
    }

}
