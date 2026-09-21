package edu.proyecto.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import edu.proyecto.dto.NotificacionArchivoRequestDTO;
import edu.proyecto.dto.NotificacionRequestDTO;
import edu.proyecto.entity.EmisorEntity;
import edu.proyecto.entity.NotificacionArchivoEntity;
import edu.proyecto.entity.NotificacionDestinatarioEntity;
import edu.proyecto.entity.NotificacionEntity;
import edu.proyecto.entity.PersonaEntity;
import edu.proyecto.repository.EmisorRepository;
import edu.proyecto.repository.NotificacionArchivoRepository;
import edu.proyecto.repository.NotificacionDestinatarioRepository;
import edu.proyecto.repository.NotificacionRepository;
import edu.proyecto.repository.PersonaRepository;
import edu.proyecto.service.NotificacionService;
import edu.proyecto.utils.EmailService;
import edu.proyecto.utils.Helper;
import edu.proyecto.utils.TemplateService;

@Service
public class NotificacionServiceImpl implements NotificacionService{
    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private NotificacionDestinatarioRepository notificacionDestinatarioRepository;
    @Autowired
    private NotificacionArchivoRepository notificacionArchivoRepository;
    @Autowired
    private EmisorRepository emisorRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private TemplateService templateService;
    @Autowired
    private EmailService emailService;
    
    @Override
    public NotificacionEntity obtenerNotificacion(Integer idNotificacion) {
        return notificacionRepository.findById(idNotificacion).get();
    }

    @Override
    public List<NotificacionEntity> listarNotificacionesPorPersona(Integer idPersona) {
        return notificacionRepository.listarNotificacionesPorPersona(idPersona);
    }

    @Override
    public List<NotificacionEntity> listarNotificacionesPorEmisor(Integer idEmisor) {
        return notificacionRepository.listarNotificacionesPorEmisor(idEmisor);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED) 
    public NotificacionEntity guardarNotificacion(NotificacionRequestDTO request) {
        
        EmisorEntity emisorEntity = emisorRepository.findById(request.getIdEmisor())
                                        .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));
        // BUSCAR PERSONA
        PersonaEntity personaEntity = personaRepository.buscarPersona(request.getTipoPersona(), request.getTipoDocumento(), request.getNroDocumento());

        NotificacionEntity notificacionEntity = new NotificacionEntity();
        notificacionEntity.setEmisor(emisorEntity);
        notificacionEntity.setFecha(Helper.getCurrentDate());
        notificacionEntity.setIdPersona(personaEntity.getIdPersona());
        notificacionEntity.setAsunto(request.getAsunto());
        notificacionEntity.setContenido(request.getContenido());
        notificacionEntity.setAlertadoCorreo(false);
        // notificacionEntity.setCorreo(personaEntity.getCorreo());
        notificacionEntity.setRegUsuarioCreacion("DEFAULT");

        notificacionEntity = notificacionRepository.save(notificacionEntity);

        NotificacionDestinatarioEntity notificacionDestinatarioEntity = new NotificacionDestinatarioEntity();
        notificacionDestinatarioEntity.setIdNotificacion(notificacionEntity.getIdNotificacion());
        notificacionDestinatarioEntity.setTipoPersona(personaEntity.getTipoPersona().getIdTipoPersona());
        notificacionDestinatarioEntity.setTipoDocIdentidad(personaEntity.getTipoDocIdentidad().getIdTipoDocIdentidad());
        notificacionDestinatarioEntity.setNroDocIdentidad(personaEntity.getNroDocumento());
        notificacionDestinatarioEntity.setNombreCompleto((personaEntity.getNombres() + " " + personaEntity.getApellidoPaterno() + " " + personaEntity.getApellidoMaterno()).trim());
        notificacionDestinatarioEntity.setRuc(personaEntity.getRuc());
        notificacionDestinatarioEntity.setRazonSocial(personaEntity.getRazonSocial());

        notificacionDestinatarioRepository.save(notificacionDestinatarioEntity);

        for (NotificacionArchivoRequestDTO archivoRequest : request.getArchivos()) {
            NotificacionArchivoEntity notificacionArchivoEntity = new NotificacionArchivoEntity();
            notificacionArchivoEntity.setNotificacion(notificacionEntity);
            notificacionArchivoEntity.setNombre(archivoRequest.getNombre());
            notificacionArchivoEntity.setRuta(archivoRequest.getRuta());
            // notificacionArchivoEntity.setActivo(true);
            notificacionArchivoRepository.save(notificacionArchivoEntity);
        }

        Context context = new Context();
        context.setVariable("nombre", personaEntity.getNombres().toUpperCase());
        String templateBody = templateService.getHtmlTemplate("alerta-notificacion", context);
        
        Boolean correoEnviado = emailService.enviarEmail(personaEntity.getCorreo(), "Alerta de notificacion", templateBody);

        return notificacionEntity;

    }

}
