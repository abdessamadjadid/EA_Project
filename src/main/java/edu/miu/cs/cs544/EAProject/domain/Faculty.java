package edu.miu.cs.cs544.EAProject.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Arrays;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
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

    public String getInitials() {
        return Arrays.stream(name.split(" "))
                .map(n -> n.substring(0, 1))
                .collect(Collectors.joining(""));
    }
}
