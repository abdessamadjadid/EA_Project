package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor
@Entity
public class Student extends Role {



    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "homeAddressId")
    private Address homeAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mailAddressId")
    private Address mailingAddress;


    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }
}
