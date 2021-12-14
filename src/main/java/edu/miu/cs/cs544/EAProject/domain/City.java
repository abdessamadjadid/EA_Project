package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Audit audit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateprovinceId")
    private StateProvince stateprovince;

    public City(String name, StateProvince stateprovince) {
        this.name = name;
        this.stateprovince = stateprovince;
    }
}
