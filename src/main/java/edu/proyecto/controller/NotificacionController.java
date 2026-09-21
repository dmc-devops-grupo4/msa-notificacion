package edu.proyecto.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.NotificacionRequestDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.entity.NotificacionEntity;
import edu.proyecto.service.NotificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Api Notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @ExceptionHandler(Exception.class)   ///obteniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @GetMapping("/{idNotificacion}")
    public ResponseEntity<NotificacionEntity> obtenerNotificacion(@PathVariable("idNotificacion") Integer idNotificacion) {
        NotificacionEntity lista = notificacionService.obtenerNotificacion(idNotificacion);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/por-persona/{idPersona}")
    public ResponseEntity<List<NotificacionEntity>> listarTramitesPorPersona(@PathVariable("idPersona") Integer idPersona) {
        List<NotificacionEntity> lista = notificacionService.listarNotificacionesPorPersona(idPersona);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/por-emisor/{idEmisor}")
    public ResponseEntity<List<NotificacionEntity>> listarTramitesPorEmisor(@PathVariable("idEmisor") Integer idEmisor) {
        List<NotificacionEntity> lista = notificacionService.listarNotificacionesPorEmisor(idEmisor);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }    

    @PostMapping
    public ResponseEntity<NotificacionEntity> guardarNotificacion(@RequestBody NotificacionRequestDTO requestDto) throws URISyntaxException{   
        NotificacionEntity notificacion = notificacionService.guardarNotificacion(requestDto); 
        return ResponseEntity.created(new URI("/"+notificacion.getIdNotificacion()))
                            .body(notificacion);
    }
}
