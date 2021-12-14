package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name",length = 255, nullable = false)
    private String name;

    @Embedded
    private CreatedModifiedDate createdModifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stateprovinceId")
    private StateProvince stateprovince;

    public City(String name, StateProvince stateprovince) {
        this.name = name;
        this.stateprovince = stateprovince;
    }

}
