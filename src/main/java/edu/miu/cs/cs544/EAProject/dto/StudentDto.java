package edu.miu.cs.cs544.EAProject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDto extends RoleDto {

    private String studentId;
    private String name;
    private String email;
}
