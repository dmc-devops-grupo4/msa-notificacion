package edu.proyecto.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.dto.EmailTramiteEnviadoDTO;
import edu.proyecto.service.EventoNotificacionService;
import edu.proyecto.utils.EmailService;
import edu.proyecto.utils.TemplateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoNotificacionServiceImpl implements EventoNotificacionService {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private EmailService emailService;

    @Async
    @Override
    public void procesarRegistroPersona(EmailRegistroPersonaDTO data) {
        try {
            log.info("nombre={}, correo = {}", data.getNombre(), data.getCorreo());

            Context context = new Context();
            context.setVariable("nombre", data.getNombre());

            String templateBody = templateService.getHtmlTemplate("registro-persona", context);

            Boolean correoEnviado = emailService.enviarEmail(data.getCorreo(), "Registro", templateBody);
            log.info("Correo enviado: {}", correoEnviado);
        } catch (Exception e) {
            log.error("Error procesando evento registro-persona", e);
        }
    }

    @Async
    @Override
    public void procesarTramiteEnviado(EmailTramiteEnviadoDTO data) {
        try {
            log.info("nombre={}, nroDocumento= {}, correo = {}, asunto={}", data.getNombre(), data.getNroDocumento(), data.getCorreo(), data.getAsunto());

            Context context = new Context();
            context.setVariable("nombre", data.getNombre());
            context.setVariable("nroDocumento", data.getNroDocumento());
            context.setVariable("asunto", data.getAsunto());

            String templateBody = templateService.getHtmlTemplate("tramite-enviado", context);

            Boolean correoEnviado = emailService.enviarEmail(data.getCorreo(), "Tramite enviado", templateBody);
            log.info("Correo enviado: {}", correoEnviado);
        } catch (Exception e) {
            log.error("Error procesando evento tramite-enviado", e);
        }
    }
}
