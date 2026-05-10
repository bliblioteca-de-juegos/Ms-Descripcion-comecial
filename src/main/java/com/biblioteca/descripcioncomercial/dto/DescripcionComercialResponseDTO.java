package com.biblioteca.descripcioncomercial.dto;

import java.time.LocalDateTime;

public record DescripcionComercialResponseDTO(
        Long id,
        Long juegoId,
        String tituloComercial,
        String descripcion,
        String requisitos,
        LocalDateTime publicadaEn,
        LocalDateTime actualizadaEn,
        JuegoDTO juego
) {
}
