package com.biblioteca.descripcioncomercial.dto;

public record JuegoDTO(
        Long id,
        String titulo,
        String descripcion,
        Double precio,
        Long categoriaId
) {
}
