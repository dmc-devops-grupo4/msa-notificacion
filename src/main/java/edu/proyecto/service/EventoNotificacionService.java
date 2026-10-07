package edu.proyecto.service;

import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.dto.EmailTramiteEnviadoDTO;

public interface EventoNotificacionService {
    void procesarRegistroPersona(EmailRegistroPersonaDTO data);
    void procesarTramiteEnviado(EmailTramiteEnviadoDTO data);
}
