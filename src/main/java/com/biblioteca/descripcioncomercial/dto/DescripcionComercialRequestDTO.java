package com.biblioteca.descripcioncomercial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DescripcionComercialRequestDTO {

    @NotNull(message = "El juegoId es obligatorio")
    private Long juegoId;

    @NotBlank(message = "El titulo comercial es obligatorio")
    @Size(max = 150, message = "El titulo comercial no puede superar 150 caracteres")
    private String tituloComercial;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 1500, message = "La descripcion no puede superar 1500 caracteres")
    private String descripcion;

    @Size(max = 1000, message = "Los requisitos no pueden superar 1000 caracteres")
    private String requisitos;
}
