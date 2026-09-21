package edu.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.entity.EmisorEntity;
import edu.proyecto.entity.ErrorEntity;
import edu.proyecto.service.EmisorService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/emisores")
@Tag(name = "Emisor", description = "Api Emisor")
public class EmisorController {

    @Autowired
    private EmisorService emisorService;

    @ExceptionHandler(Exception.class)   ///obteniendo el error de excepcion en forma generica
    private ErrorEntity capturadorErrores(Exception ex){
        ErrorEntity error = new ErrorEntity(HttpStatus.CONFLICT.toString(), "Problema interno :)", "A ocurrido un error: "+ex.getMessage());
        return  error;
    }

    @GetMapping()
    public ResponseEntity<List<EmisorEntity>> listarEmisores() {
        List<EmisorEntity> lista = emisorService.listarEmisores();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

}
