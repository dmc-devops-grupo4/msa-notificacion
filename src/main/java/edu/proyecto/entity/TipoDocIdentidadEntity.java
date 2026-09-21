package edu.proyecto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocIdentidadEntity {
    private Integer idTipoDocIdentidad; 
    private String descripcion;
}
