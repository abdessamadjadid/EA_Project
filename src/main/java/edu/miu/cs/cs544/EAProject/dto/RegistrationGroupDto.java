package edu.miu.cs.cs544.EAProject.dto;

import edu.miu.cs.cs544.EAProject.domain.RegistrationEvent;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;

import javax.persistence.*;
import java.util.Collection;

public class RegistrationGroupDto {

    private int id;

    private String name;

    private Collection<AcademicBlockDto> academicBlockDtos;

    private RegistrationEvent registrationEvent;

    private Audit audit;

}
