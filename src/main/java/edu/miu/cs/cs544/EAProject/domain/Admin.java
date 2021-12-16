package edu.miu.cs.cs544.EAProject.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Entity
public class Admin extends Role {
}
