package edu.miu.cs.cs544.EAProject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Collection<RegistrationRequest> registrationRequests;

    @ManyToMany
    @JoinTable(name = "studentRegistrationGroup",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "registrationGroupId"))
    private Collection<RegistrationGroup> registrationGroups = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Collection<Registration> registrations;

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
                   Collection<RegistrationRequest> registrationRequests, Collection<RegistrationGroup> registrationGroups) {

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.homeAddress = homeAddress;
        this.mailingAddress = mailingAddress;
        this.registrationRequests = registrationRequests;
        this.registrationGroups = registrationGroups;
    }

    public void addRegistrationGroup(RegistrationGroup registrationGroup) {
        this.registrationGroups.add(registrationGroup);
        registrationGroup.addStudent(this);
    }

    public void addRegistration(Registration registration) {
        this.registrations.add(registration);
    }
}
