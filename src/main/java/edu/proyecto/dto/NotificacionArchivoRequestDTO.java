package edu.proyecto.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionArchivoRequestDTO {
    private String nombre;
    private String ruta;
}
