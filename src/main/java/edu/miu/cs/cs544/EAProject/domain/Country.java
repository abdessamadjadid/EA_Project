package edu.miu.cs.cs544.EAProject.domain;
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

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Embedded
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateprovinceId")
    private List<StateProvince> stateprovince;

    public Country(String name ,List<StateProvince> stateprovince) {
        this.name = name;
        this.stateprovince = stateprovince;
    }

}
