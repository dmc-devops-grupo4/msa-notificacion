package edu.proyecto.service;

import java.util.List;

import edu.proyecto.dto.NotificacionRequestDTO;
import edu.proyecto.entity.NotificacionEntity;

public interface NotificacionService {
    public NotificacionEntity obtenerNotificacion(Integer idNotificacion);
    public List<NotificacionEntity> listarNotificacionesPorPersona(Integer idPersona);
    public List<NotificacionEntity> listarNotificacionesPorEmisor(Integer idEmisor);
    public NotificacionEntity guardarNotificacion(NotificacionRequestDTO requestDto);
}
