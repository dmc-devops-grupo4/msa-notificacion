package edu.proyecto.repository;

import edu.proyecto.entity.PersonaEntity;

public interface PersonaRepository {
    public PersonaEntity buscarPersona(Integer idTipoPersona,Integer idTipoDocIdentidadm, String nroDocumento);
}
