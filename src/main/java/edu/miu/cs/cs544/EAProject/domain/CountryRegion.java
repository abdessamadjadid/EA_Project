package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CountryRegion")
@NoArgsConstructor
@Data
public class CountryRegion {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    @Size(max = 2, min = 2)
    private String code;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateId")
    @OrderBy("name")
    private List<StateProvince> stateProvinces;

    public CountryRegion(String name, String code, List<StateProvince> stateProvinces) {
        this.name = name;
        this.code = code;
        this.stateProvinces = stateProvinces;
    }
}
