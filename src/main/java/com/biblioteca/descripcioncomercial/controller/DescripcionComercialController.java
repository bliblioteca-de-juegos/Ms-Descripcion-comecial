package com.biblioteca.descripcioncomercial.controller;

import com.biblioteca.descripcioncomercial.dto.DescripcionComercialRequestDTO;
import com.biblioteca.descripcioncomercial.dto.DescripcionComercialResponseDTO;
import com.biblioteca.descripcioncomercial.service.DescripcionComercialService;
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
@RequestMapping("/api/descripciones-comerciales")
@RequiredArgsConstructor
public class DescripcionComercialController {

    private final DescripcionComercialService descripcionComercialService;

    @GetMapping
    public List<DescripcionComercialResponseDTO> obtenerTodas() {
        return descripcionComercialService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DescripcionComercialResponseDTO> obtenerPorId(@PathVariable Long id) {
        return descripcionComercialService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<DescripcionComercialResponseDTO> obtenerPorJuego(@PathVariable Long juegoId) {
        return descripcionComercialService.obtenerPorJuego(juegoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DescripcionComercialResponseDTO> crear(
            @Valid @RequestBody DescripcionComercialRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(descripcionComercialService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DescripcionComercialResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody DescripcionComercialRequestDTO dto) {
        return descripcionComercialService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        descripcionComercialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
