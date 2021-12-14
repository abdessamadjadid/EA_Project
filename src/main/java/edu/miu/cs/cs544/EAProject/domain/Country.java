package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Country")

public class Country {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Audit createdModifiedDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateProvinceId")
    private List<StateProvince> stateProvinces;

    public Country(String name, List<StateProvince> stateProvince) {
        this.name = name;
        this.stateProvinces = stateProvince;
    }


}
