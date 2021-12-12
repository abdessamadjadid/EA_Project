package edu.miu.cs.cs544.EAProject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

}
