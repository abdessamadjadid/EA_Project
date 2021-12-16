package edu.miu.cs.cs544.EAProject.domain;

import edu.miu.cs.cs544.EAProject.domain.audit.Audit;
import edu.miu.cs.cs544.EAProject.domain.audit.AuditListener;
import edu.miu.cs.cs544.EAProject.domain.audit.Auditable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`Role`")
@EntityListeners(AuditListener.class)
public abstract class Role implements Auditable {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    @Access(AccessType.PROPERTY)
    private String roleName;

    @Embedded
    private Audit audit;

    public String getRoleName() {
        return this.getClass().getSimpleName().toLowerCase();
    }
}
