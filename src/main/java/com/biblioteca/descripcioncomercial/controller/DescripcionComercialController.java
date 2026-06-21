package com.biblioteca.descripcioncomercial.controller;

import com.biblioteca.descripcioncomercial.dto.DescripcionComercialRequestDTO;
import com.biblioteca.descripcioncomercial.dto.DescripcionComercialResponseDTO;
import com.biblioteca.descripcioncomercial.service.DescripcionComercialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/descripciones-comerciales")
@Tag(name = "Descripcion comercial", description = "Operaciones de descripciones comerciales de juegos")
@RequiredArgsConstructor
public class DescripcionComercialController {

    private final DescripcionComercialService descripcionComercialService;

    @GetMapping
    @Operation(summary = "Listar todas las descripciones comerciales")
    public List<DescripcionComercialResponseDTO> obtenerTodas() {
        return descripcionComercialService.obtenerTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una descripcion comercial por ID")
    public ResponseEntity<DescripcionComercialResponseDTO> obtenerPorId(@PathVariable Long id) {
        return descripcionComercialService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/juego/{juegoId}")
    @Operation(summary = "Obtener la descripcion comercial de un juego")
    public ResponseEntity<DescripcionComercialResponseDTO> obtenerPorJuego(@PathVariable Long juegoId) {
        return descripcionComercialService.obtenerPorJuego(juegoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear una descripcion comercial")
    public ResponseEntity<DescripcionComercialResponseDTO> crear(
            @Valid @RequestBody DescripcionComercialRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(descripcionComercialService.crear(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una descripcion comercial")
    public ResponseEntity<DescripcionComercialResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DescripcionComercialRequestDTO dto) {
        return descripcionComercialService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una descripcion comercial")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descripcionComercialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
