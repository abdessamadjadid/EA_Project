package edu.miu.cs.cs544.EAProject.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
}
