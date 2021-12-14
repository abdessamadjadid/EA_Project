package edu.miu.cs.cs544.EAProject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

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
    private CreatedModifiedDate createdModifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private Country country;

    public StateProvince(String name, String code, Country country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }

}
