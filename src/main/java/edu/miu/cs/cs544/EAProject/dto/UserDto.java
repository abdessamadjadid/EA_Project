package edu.miu.cs.cs544.EAProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "{error.password.blank}")
    private String username;

    @Min(value = 5, message = "{error.password.weak}")
    @NotBlank(message = "{error.password.blank}")
    private String password;
}
