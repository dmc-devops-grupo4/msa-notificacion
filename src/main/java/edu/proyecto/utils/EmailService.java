package edu.proyecto.utils;

public interface EmailService {
    public boolean enviarEmail(String to, String subject, String text);
}
