package edu.proyecto.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.proyecto.dto.EmailRegistroPersonaDTO;
import edu.proyecto.utils.EmailService;
import edu.proyecto.utils.TemplateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonaConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private EmailService emailService;
    
    @KafkaListener(topics="${topico.registro-persona}")
    public void consumirRegistroPersona(String message){
        try {
            EmailRegistroPersonaDTO data = objectMapper.readValue(message, EmailRegistroPersonaDTO.class);
        
            log.info("nombre={}, correo = {}", data.getNombre(), data.getCorreo());
            
            Context context = new Context();
            context.setVariable("nombre", data.getNombre());

            String templateBody = templateService.getHtmlTemplate("registro-persona", context);

            Boolean correoEnviado = emailService.enviarEmail(data.getCorreo(), "Registro", templateBody);
            log.info("Correo enviado: {}", correoEnviado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}