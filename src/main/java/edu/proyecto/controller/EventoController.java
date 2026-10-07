package edu.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.dto.EmailTramiteEnviadoDTO;
import edu.proyecto.service.EventoNotificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Recibe eventos de otros microservicios. Responde 202 y procesa en segundo plano.
 */
@RestController
@RequestMapping("api/v1/eventos")
@Tag(name = "Eventos", description = "Recepcion de eventos de otros microservicios")
public class EventoController {

    @Autowired
    private EventoNotificacionService eventoNotificacionService;

    @PostMapping("/registro-persona")
    public ResponseEntity<Void> registroPersona(@RequestBody EmailRegistroPersonaDTO data) {
        eventoNotificacionService.procesarRegistroPersona(data);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/tramite-enviado")
    public ResponseEntity<Void> tramiteEnviado(@RequestBody EmailTramiteEnviadoDTO data) {
        eventoNotificacionService.procesarTramiteEnviado(data);
        return ResponseEntity.accepted().build();
    }
}
