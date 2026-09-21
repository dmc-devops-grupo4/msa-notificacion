package edu.proyecto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notificacion_destinatario")
public class NotificacionDestinatarioEntity {
    @Id
    @Column(name = "id_notificacion")
    private Integer idNotificacion; 

    @Column(name = "tipo_persona")
    private Integer tipoPersona;

    @Column(name = "tipo_doc_identidad")
    private Integer tipoDocIdentidad;

    @Column(name = "nro_doc_identidad")
    private String nroDocIdentidad;

    @Column(name="nombre_completo")
    private String nombreCompleto;

    @Column(name="ruc")
    private String ruc;

    @Column(name="razon_social")
    private String razonSocial;
}
