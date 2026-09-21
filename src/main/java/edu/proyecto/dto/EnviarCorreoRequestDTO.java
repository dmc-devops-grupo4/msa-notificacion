package edu.proyecto.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviarCorreoRequestDTO {
    private String to;
    private String subject;
    private String body;
}
