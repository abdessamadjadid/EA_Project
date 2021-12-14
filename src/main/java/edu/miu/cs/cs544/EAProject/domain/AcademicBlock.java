package edu.miu.cs.cs544.EAProject.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private CreatedModifiedDate createdModifiedDate;

    //@ManyToMany(cascade = CascadeType.ALL, mappedBy = "")
    @JoinColumn(name = "blockregistrationgroupId")
    private List<RegistrationGroup> registrationGroup;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseOfferingsId")
    private  List<CourseOffering> courseOfferings;


    public AcademicBlock(RegistrationGroup registrationGroup, CourseOffering courseOffering) {
        this.registrationGroup = (List<RegistrationGroup>) registrationGroup;
        this.courseOfferings = (List<CourseOffering>) courseOffering;
    }

}
