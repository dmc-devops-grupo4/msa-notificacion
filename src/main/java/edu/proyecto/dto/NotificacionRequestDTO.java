package edu.proyecto.dto;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionRequestDTO {
    private Integer idEmisor;
    private Integer tipoPersona;
    private Integer tipoDocumento;
    private String nroDocumento;
    private String asunto;
    private String contenido;
    private List<NotificacionArchivoRequestDTO> archivos;
}
