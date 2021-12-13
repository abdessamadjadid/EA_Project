package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "StateProvince")
@NoArgsConstructor
@Data
public class StateProvince {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    @OrderBy("name")
    private List<City> cities;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    public StateProvince(String name, String code, List<City> cities) {
        this.name = name;
        this.code = code;
        this.cities = cities;
    }
}
