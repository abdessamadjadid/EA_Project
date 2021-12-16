package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationEventDto {

    private Integer id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
