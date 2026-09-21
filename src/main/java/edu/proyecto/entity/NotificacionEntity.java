package edu.proyecto.entity;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notificacion")
@Schema(name = "Notificacion", description = "Entity de Notificacion")
public class NotificacionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_notificacion")
    private Integer idNotificacion;

    @OneToOne
    @JoinColumn(name = "id_emisor", updatable = false, nullable = false)
    private EmisorEntity emisor;

    @OneToOne
    @JoinColumn(name = "id_notificacion", updatable = false, nullable = true)
    private NotificacionDestinatarioEntity destinatario;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="id_persona")
    private Integer idPersona;

    @Column(name="asunto")
    private String asunto;

    @Column(name="contenido")
    private String contenido;

    @Column(name="leido", insertable = false, nullable = false)
    private Boolean leido;

    @Column(name="fecha_leido", insertable = false, nullable = true)
    private Date fechaLeido;

    @Column(name="destacado", insertable = false, nullable = false)
    private Boolean destacado;

    @Column(name="alertado_correo")
    private Boolean alertadoCorreo;

    @Column(name="correo")
    private String correo;

    @Column(name="activo", insertable = false, nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "notificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificacionArchivoEntity> archivos;

    // AUDITORIA
    @Column(name="reg_fecha_creacion", insertable = false, updatable = false, nullable = false)
    private Date regFechaCreacion;
    @Column(name="reg_usuario_creacion", nullable = false)
    private String regUsuarioCreacion;
    @Column(name="reg_ip_creacion")
    private String regIpCreacion;
    @Column(name="reg_fecha_modificacion")
    private Date regFechaModificacion;
    @Column(name="reg_usuario_modificacion")
    private String regUsuarioModificacion;
    @Column(name="reg_ip_modificacion")
    private String regIpModificacion;


}
