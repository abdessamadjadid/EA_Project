package edu.miu.cs.cs544.EAProject.domain;
import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "courseId")
    private Collection<Course> course;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "academicblockId")
    private  List<AcademicBlock> academicBlock;


    public CourseOffering(String name, String code, String facultyInitials, int capacity ,List<AcademicBlock> academicBlock, Faculty faculty, Collection<Course> course) {
        this.academicBlock = academicBlock;
        this.facultyInitials = facultyInitials;
        this.capacity = capacity;
        this.academicBlock = academicBlock;
        this.faculty = faculty;
        this.course = course;

    }

    public int getAvailableSeats()
    {
        return 0;
    }


}
