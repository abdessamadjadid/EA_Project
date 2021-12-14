package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "StateProvince")
@NoArgsConstructor

public class StateProvince {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Column(name = "code", length = 255, nullable = false)
    private String code;

    @Embedded
    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cityId")
    private List<City> city;

    public StateProvince(String name, String code, List<City> city) {
        this.name = name;
        this.code = code;
        this.city = city;
    }
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private Country country;

    public StateProvince(String name, String code, Country country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }
}
