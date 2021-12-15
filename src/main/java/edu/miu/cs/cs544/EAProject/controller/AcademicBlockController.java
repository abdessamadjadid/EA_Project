package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityListeners;

@RestController
@RequestMapping("/academic-blocks")
@EntityListeners(AuditListener.class)
public class AcademicBlockController {

    @Autowired
    private AcademicBlockService service;

}
