package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.RegistrationGroup;
import edu.miu.cs.cs544.EAProject.service.RegistrationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration-groups")
public class RegistrationGroupController {

    @Autowired
    private RegistrationGroupService service;

    @PostMapping
    private RegistrationGroup create(@RequestBody RegistrationGroup group){
        return service.createGroup(group);
    }

}
