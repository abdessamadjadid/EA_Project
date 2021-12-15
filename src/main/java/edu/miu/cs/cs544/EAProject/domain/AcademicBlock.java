package edu.miu.cs.cs544.EAProject.domain;


import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "AcademicBlock")
@NoArgsConstructor
public class AcademicBlock {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    public enum Semester {
        ONE, TWO, THREE, FOUR
    }

    @Embedded
    private StartEndDate startEndDate;

    @Embedded
    private Audit audit;

    /*//@ManyToMany(cascade = CascadeType.ALL, mappedBy = "")
    @JoinColumn(name = "blockregistrationgroupId")
    private List<RegistrationGroup> registrationGroup;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicBlock")
    private Collection<CourseOffering> courseOfferings;



}
