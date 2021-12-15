package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @Column(name = "code", nullable = false)
    @Size(max = 2, min = 2)
    private String code;

    @Embedded
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateProvinceId")
    private List<StateProvince> stateProvinces;

    public Country(String name, String code, List<StateProvince> stateProvince) {
        this.name = name;
        this.code = code;
        this.stateProvinces = stateProvince;
    }
}
