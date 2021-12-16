package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    private Integer studentId;
    private Integer courseOfferingId;
    private Integer registrationEventId;
    private Integer priority;


}
