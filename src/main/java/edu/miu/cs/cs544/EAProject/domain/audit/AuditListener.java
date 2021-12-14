package edu.miu.cs.cs544.EAProject.domain.audit;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Optional;

public class AuditListener {

    @PrePersist
    public void setCreatedDate(Auditable auditable) {

        Audit audit = Optional.ofNullable(auditable.getAudit())
                        .orElseGet(() -> {
                            Audit aud = new Audit();
                            auditable.setAudit(aud);
                            return aud;
                        });

        audit.setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void setLastModifiedDate(Auditable auditable) {

        Audit audit = Optional.ofNullable(auditable.getAudit())
                .orElseGet(() -> {
                    Audit aud = new Audit();
                    auditable.setAudit(aud);
                    return aud;
                });

        audit.setModifiedDate(LocalDateTime.now());
    }
}
