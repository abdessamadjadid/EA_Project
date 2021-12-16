package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class RegistrationGroup implements Auditable {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "registrationGroups", cascade = CascadeType.ALL)
    private Set<AcademicBlock> academicBlocks = new HashSet<>();

    @ManyToMany(mappedBy = "registrationGroups", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "registrationEventId")
    private RegistrationEvent registrationEvent;

    @Embedded
    private Audit audit;

    public RegistrationGroup(String name, Set<AcademicBlock> academicBlocks, RegistrationEvent registrationEvent) {
        this.name = name;
        this.registrationEvent = registrationEvent;

        if (academicBlocks != null) {
            academicBlocks.forEach(this::addAcademicBlock);
        } else {
            this.academicBlocks = null;
        }
    }

    public RegistrationGroup(String name, Set<AcademicBlock> academicBlocks, Set<Student> students, RegistrationEvent registrationEvent) {
        this.name = name;
        this.academicBlocks = academicBlocks;
        this.students = students;
        this.registrationEvent = registrationEvent;
    }

    public void addAcademicBlock(AcademicBlock academicBlock) {
        this.academicBlocks.add(academicBlock);
        academicBlock.addRegistrationGroup(this);
    }

    public void addStudent(Student student) {
        students.add(student);
    }
}
