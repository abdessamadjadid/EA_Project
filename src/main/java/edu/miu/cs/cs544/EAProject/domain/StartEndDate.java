package edu.miu.cs.cs544.EAProject.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartEndDate
{
    @Column(name = "StartDate")
    private LocalDateTime startdate;

    @Column(name = "EndDate")
    private LocalDateTime enddate;

}
