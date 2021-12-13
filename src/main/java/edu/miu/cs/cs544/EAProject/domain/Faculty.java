package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Faculty extends Role {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String title;
}
