package edu.miu.cs.cs544.EAProject.domain;
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
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "academicblockId")
    private  List<AcademicBlock> academicBlock;


    public CourseOffering(String name, String code, String facultyInitials, int capacity ,List<AcademicBlock> academicBlock) {
        this.academicBlock = academicBlock;
    }

    public int getAvailableSeats()
    {
        return 0;
    }


}
