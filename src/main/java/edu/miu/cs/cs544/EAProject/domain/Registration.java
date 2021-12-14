package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "Registration")
@NoArgsConstructor
public class Registration
{
    @Id
    @GeneratedValue
    private int id;

    @Embedded
    private Audit audit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private Student student;

    public Registration(Student student){
        this.student = student;
    }
}
