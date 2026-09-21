package edu.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.NotificacionDestinatarioEntity;

@Repository
public interface NotificacionDestinatarioRepository extends JpaRepository<NotificacionDestinatarioEntity, Integer>{
    
}