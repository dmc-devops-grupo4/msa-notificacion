package edu.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.proyecto.entity.NotificacionEntity;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Integer>{

    @Query(value = "SELECT * FROM notificacion WHERE id_persona=:idPersona AND activo=1 ORDER BY fecha DESC", nativeQuery = true)
    public List<NotificacionEntity> listarNotificacionesPorPersona(@Param("idPersona")Integer idPersona);

    @Query(value = "SELECT * FROM notificacion WHERE id_emisor=:idEmisor AND activo=1 ORDER BY fecha DESC", nativeQuery = true)
    public List<NotificacionEntity> listarNotificacionesPorEmisor(@Param("idEmisor")Integer idEmisor);
}