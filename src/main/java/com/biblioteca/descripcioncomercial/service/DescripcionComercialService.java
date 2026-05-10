package com.biblioteca.descripcioncomercial.service;

import com.biblioteca.descripcioncomercial.client.JuegoClient;
import com.biblioteca.descripcioncomercial.dto.DescripcionComercialRequestDTO;
import com.biblioteca.descripcioncomercial.dto.DescripcionComercialResponseDTO;
import com.biblioteca.descripcioncomercial.dto.JuegoDTO;
import com.biblioteca.descripcioncomercial.model.DescripcionComercial;
import com.biblioteca.descripcioncomercial.repository.DescripcionComercialRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DescripcionComercialService {

    private final DescripcionComercialRepository descripcionComercialRepository;
    private final JuegoClient juegoClient;

    public List<DescripcionComercialResponseDTO> obtenerTodas() {
        return descripcionComercialRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<DescripcionComercialResponseDTO> obtenerPorId(Long id) {
        return descripcionComercialRepository.findById(id).map(this::mapToDTO);
    }

    public Optional<DescripcionComercialResponseDTO> obtenerPorJuego(Long juegoId) {
        validarJuego(juegoId);
        return descripcionComercialRepository.findByJuegoId(juegoId).map(this::mapToDTO);
    }

    @Transactional
    public DescripcionComercialResponseDTO crear(DescripcionComercialRequestDTO dto) {
        validarJuego(dto.getJuegoId());

        if (descripcionComercialRepository.existsByJuegoId(dto.getJuegoId())) {
            throw new IllegalArgumentException("El juego ya tiene una descripcion comercial");
        }

        DescripcionComercial descripcion = new DescripcionComercial(
                null,
                dto.getJuegoId(),
                dto.getTituloComercial(),
                dto.getDescripcion(),
                dto.getRequisitos(),
                LocalDateTime.now(),
                null
        );

        return mapToDTO(descripcionComercialRepository.save(descripcion));
    }

    @Transactional
    public Optional<DescripcionComercialResponseDTO> actualizar(Long id, DescripcionComercialRequestDTO dto) {
        validarJuego(dto.getJuegoId());

        return descripcionComercialRepository.findById(id).map(descripcion -> {
            descripcion.setJuegoId(dto.getJuegoId());
            descripcion.setTituloComercial(dto.getTituloComercial());
            descripcion.setDescripcion(dto.getDescripcion());
            descripcion.setRequisitos(dto.getRequisitos());
            descripcion.setActualizadaEn(LocalDateTime.now());
            return mapToDTO(descripcionComercialRepository.save(descripcion));
        });
    }

    @Transactional
    public void eliminar(Long id) {
        if (!descripcionComercialRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe una descripcion comercial con id " + id);
        }
        descripcionComercialRepository.deleteById(id);
    }

    private DescripcionComercialResponseDTO mapToDTO(DescripcionComercial descripcion) {
        return new DescripcionComercialResponseDTO(
                descripcion.getId(),
                descripcion.getJuegoId(),
                descripcion.getTituloComercial(),
                descripcion.getDescripcion(),
                descripcion.getRequisitos(),
                descripcion.getPublicadaEn(),
                descripcion.getActualizadaEn(),
                obtenerJuegoSeguro(descripcion.getJuegoId())
        );
    }

    private void validarJuego(Long juegoId) {
        try {
            juegoClient.obtenerJuego(juegoId);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("No existe un juego con id " + juegoId);
        }
    }

    private JuegoDTO obtenerJuegoSeguro(Long juegoId) {
        try {
            return juegoClient.obtenerJuego(juegoId);
        } catch (FeignException e) {
            return null;
        }
    }
}
