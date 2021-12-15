package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "student")
    private Collection<RegistrationRequest> registrationRequests;

    @ManyToMany
    @JoinTable(name = "Registration",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseOfferingId"))
    private Collection<CourseOffering> courseOfferings;

    @ManyToMany
    @JoinTable(name = "studentRegistrationGroup",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "registrationGroupId"))
    private Collection<RegistrationGroup> registrationGroups;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public Student(String studentId, String name, String email, Address mailingAddress, Address homeAddress) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.mailingAddress = mailingAddress;
        this.homeAddress = homeAddress;
    }

    public Student(String studentId, String name, String email, Address homeAddress, Address mailingAddress,
                   Collection<RegistrationRequest> registrationRequests, Collection<CourseOffering> courseOfferings, Collection<RegistrationGroup> registrationGroups) {

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.homeAddress = homeAddress;
        this.mailingAddress = mailingAddress;
        this.registrationRequests = registrationRequests;
        this.courseOfferings = courseOfferings;
        this.registrationGroups = registrationGroups;
    }

    public Boolean addCourse(CourseOffering courseOffering){
        if(!this.courseOfferings.contains(courseOffering)){
            this.courseOfferings.add(courseOffering);
            return true;
        }
        return false;
    }
}
