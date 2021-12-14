package edu.miu.cs.cs544.EAProject.domain.audit;

public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
