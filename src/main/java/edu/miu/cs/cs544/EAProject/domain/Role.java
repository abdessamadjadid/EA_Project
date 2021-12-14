package edu.miu.cs.cs544.EAProject.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`Role`")
public abstract class Role {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    @Access(AccessType.PROPERTY)
    private String roleName;

    public String getRoleName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
