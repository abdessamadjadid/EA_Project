package edu.miu.cs.cs544.EAProject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
@NoArgsConstructor
@Getter
public class Address {

    @Id
    @GeneratedValue
    private int id;

}
