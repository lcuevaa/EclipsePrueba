package com.cueva.denuncia_service.entity;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "denuncia")
@EntityListeners(AuditingEntityListener.class)
public class Denuncia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "dni", unique = true, nullable = false, length = 8)
    private String dni;
    
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;
    
    @Column(name = "titulo", nullable = false, length = 3)
    private String titulo;

    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;
    
    @Column(name = "descripcion", length = 250)
    private String descripcion;
    
    @Column(name = "creted_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

}
