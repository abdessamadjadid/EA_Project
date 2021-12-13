package edu.miu.cs.cs544.EAProject.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "`User`")
public class User {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @JoinTable(name = "user_role")
    @ManyToMany
    private Collection<Role> roles;

    @Embedded
    private Audit audit;

}
