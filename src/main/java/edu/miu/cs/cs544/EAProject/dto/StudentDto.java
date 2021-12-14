package edu.miu.cs.cs544.EAProject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDto extends RoleDto {

    @NotBlank(message = "{error.studentId.blank}")
    private String studentId;

    @NotBlank(message = "{error.name.blank}")
    private String name;

    @NotBlank(message = "{error.email.blank}")
    @Email(message = "{error.email.invalid}")
    private String email;
}
