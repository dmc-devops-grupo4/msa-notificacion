package edu.proyecto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaEntity {
    private Integer idPersona;
    private TipoDocIdentidadEntity tipoDocIdentidad;
    private TipoPersonaEntity tipoPersona;
    private String nroDocumento;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String correo;
    private String celular;
    private String ruc;
    private String razonSocial;
    private Boolean activo;
}
