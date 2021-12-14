package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "CourseOffering")
@NoArgsConstructor

public class CourseOffering {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "facultyInitials", nullable = false)
    private String facultyInitials;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Embedded
    private Audit createdModifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academicBlockId")
    private AcademicBlock academicBlock;


    public CourseOffering(String name, String code, String facultyInitials, int capacity, AcademicBlock academicBlock) {
        this.academicBlock = academicBlock;
    }

    public int getAvailableSeats() {
        return 0;
    }

}
