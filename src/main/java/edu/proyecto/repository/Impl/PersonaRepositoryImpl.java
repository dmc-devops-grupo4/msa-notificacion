package edu.proyecto.repository.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import edu.proyecto.entity.PersonaEntity;
import edu.proyecto.repository.PersonaRepository;

@Repository
public class PersonaRepositoryImpl implements PersonaRepository {
    private RestTemplate restTemplate;

    @Value("${uri.service.persona}")
    private String urlApiPersona;

    public PersonaRepositoryImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public PersonaEntity buscarPersona(Integer idTipoPersona, Integer idTipoDocIdentidad, String nroDocumento) {
        String url = UriComponentsBuilder.fromHttpUrl(urlApiPersona + "/api/v1/personas/buscar")
            .queryParam("idTipoPersona", idTipoPersona)
            .queryParam("idTipoDocIdentidad", idTipoDocIdentidad)
            .queryParam("nroDocumento", nroDocumento)
            .toUriString();

        PersonaEntity persona = restTemplate.getForObject(url, PersonaEntity.class);
        System.out.println("persona => " + persona);
        return persona;
    }
}
