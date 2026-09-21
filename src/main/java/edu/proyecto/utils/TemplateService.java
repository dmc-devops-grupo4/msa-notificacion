package edu.proyecto.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class TemplateService {

    @Autowired
    private TemplateEngine templateEngine;

    public String getHtmlTemplate(String templateName, Context context) {
        try {
            // Load the HTML template from resources
            // ClassPathResource resource = new ClassPathResource("templates/" + templateName);
            // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            // System.out.println(resource);
            // String content = new String(Files.readAllBytes(Paths.get(resource.getURI())));
            // System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB content");
            // System.out.println(content);
            // Generate the HTML body using Thymeleaf
            // return templateEngine.process(content, context);
            return templateEngine.process(templateName, context);

        // } catch (IOException e) {
        //     e.printStackTrace(); // Manejo de IOException
        //     throw new RuntimeException("Error al leer la plantilla de correo electrónico", e);
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de excepciones generales
            throw new RuntimeException("Error al procesar la plantilla de correo electrónico", e);
        }
    }
}
