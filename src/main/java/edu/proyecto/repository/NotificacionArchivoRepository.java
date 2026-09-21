package edu.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.NotificacionArchivoEntity;

@Repository
public interface NotificacionArchivoRepository extends JpaRepository<NotificacionArchivoEntity, Integer>{
    
}