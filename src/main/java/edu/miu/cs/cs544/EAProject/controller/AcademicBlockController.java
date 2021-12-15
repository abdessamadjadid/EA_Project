package edu.miu.cs.cs544.EAProject.controller;

import edu.miu.cs.cs544.EAProject.domain.AcademicBlock;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.dto.BlockDto;
import edu.miu.cs.cs544.EAProject.service.AcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityListeners;
import javax.validation.Valid;

@RestController
@RequestMapping("/academic-blocks")
@EntityListeners(AuditListener.class)
public class AcademicBlockController {

    @Autowired
    private AcademicBlockService service;

    @GetMapping("/{id}")
    public AcademicBlock getAcademicBlockById(@Valid @PathVariable(name = "id") Integer id) {
        return service.getAcademicBlockById(id);
    }

    @PostMapping
    public AcademicBlock saveAcademicBlock(@Valid @RequestBody BlockDto block) {
        AcademicBlock academicBlock = new AcademicBlock(block.getCode(), block.getName(), block.getSemester(), block.getStartDate(), block.getEndDate());
        return service.saveAcademicBlock(academicBlock);
    }

    @PutMapping
    public AcademicBlock updateAcademicBlock(@Valid @RequestBody AcademicBlock course) {
        return service.updateAcademicBlock(course);
    }

}
