package edu.miu.cs.cs544.EAProject.domain;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`Role`")
public abstract class Role {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    private String roleName;
}
