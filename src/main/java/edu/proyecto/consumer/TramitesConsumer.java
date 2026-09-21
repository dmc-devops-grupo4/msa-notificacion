package edu.proyecto.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.proyecto.dto.EmailTramiteEnviadoDTO;
import edu.proyecto.utils.EmailService;
import edu.proyecto.utils.TemplateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TramitesConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics="${topico.tramite-enviado}")
    public void consumirTramiteEnviado(String message){
        try {
            EmailTramiteEnviadoDTO data = objectMapper.readValue(message, EmailTramiteEnviadoDTO.class);
        
            log.info("nombre={}, nroDocumento= {}, correo = {}, asunto={}", data.getNombre(), data.getNroDocumento(), data.getCorreo(), data.getAsunto());
            
            Context context = new Context();
            context.setVariable("nombre", data.getNombre());
            context.setVariable("nroDocumento", data.getNroDocumento());
            context.setVariable("asunto", data.getAsunto());

            String templateBody = templateService.getHtmlTemplate("tramite-enviado", context);

            Boolean correoEnviado = emailService.enviarEmail(data.getCorreo(), "Tramite enviado", templateBody);
            log.info("Correo enviado: {}", correoEnviado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}