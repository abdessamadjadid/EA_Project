package edu.miu.cs.cs544.EAProject.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class FacultyDto extends RoleDto{

    private String name;
    private String email;
    private String title;
}
