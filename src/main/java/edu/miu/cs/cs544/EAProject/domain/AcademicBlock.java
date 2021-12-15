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

    public enum Semester {
        SPRING, SUMMER, WINTER
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdDate", column = @Column(name = "startDate")),
            @AttributeOverride(name = "modifiedDate", column = @Column(name = "endDate"))
    })
    private Audit startEndDate;

    @Embedded
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blockRegistrationGroupId")
    private List<BlockRegistrationGroup> blockRegistrationGroups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicBlock")
    private List<CourseOffering> courseOfferings;


    public AcademicBlock(BlockRegistrationGroup blockRegistrationGroup, CourseOffering courseOffering) {
        this.blockRegistrationGroups.add(blockRegistrationGroup);
        this.courseOfferings.add(courseOffering);
    }

}
