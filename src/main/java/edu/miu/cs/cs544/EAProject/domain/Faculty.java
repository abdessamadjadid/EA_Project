package edu.miu.cs.cs544.EAProject.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Faculty extends Role {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String title;

    public Faculty(String name, String email, String title) {
        this.name = name;
        this.email = email;
        this.title = title;
    }
}
