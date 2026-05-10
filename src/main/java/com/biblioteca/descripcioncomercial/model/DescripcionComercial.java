package com.biblioteca.descripcioncomercial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "descripciones_comerciales",
        uniqueConstraints = @UniqueConstraint(columnNames = "juego_id")
)
public class DescripcionComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "juego_id", nullable = false)
    private Long juegoId;

    @Column(nullable = false, length = 150)
    private String tituloComercial;

    @Column(nullable = false, length = 1500)
    private String descripcion;

    @Column(length = 1000)
    private String requisitos;

    @Column(name = "publicada_en", nullable = false)
    private LocalDateTime publicadaEn;

    @Column(name = "actualizada_en")
    private LocalDateTime actualizadaEn;
}
