package edu.proyecto.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.dto.EnviarCorreoRequestDTO;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.utils.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/correos")
@Tag(name = "Correo", description = "Api Correo")
public class CorreoController {

    @Autowired
    private EmailService emailService;
    
    @ExceptionHandler(Exception.class)   ///obteniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @PostMapping
    public ResponseEntity<String> enviarCorreo(@RequestBody EnviarCorreoRequestDTO requestDto) throws URISyntaxException{   
        Boolean correoEnviado = emailService.enviarEmail(requestDto.getTo(), requestDto.getSubject(), requestDto.getBody());

        if (correoEnviado) {
            return ResponseEntity.created(new URI("/api/v1/correos")).body(correoEnviado.toString());
        } else {
            return ResponseEntity.status(500).body("Error al enviar el correo");
        }
    }
}
