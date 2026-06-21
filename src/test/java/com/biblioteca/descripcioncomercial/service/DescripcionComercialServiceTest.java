package com.biblioteca.descripcioncomercial.service;

import com.biblioteca.descripcioncomercial.client.JuegoClient;
import com.biblioteca.descripcioncomercial.dto.DescripcionComercialResponseDTO;
import com.biblioteca.descripcioncomercial.model.DescripcionComercial;
import com.biblioteca.descripcioncomercial.repository.DescripcionComercialRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DescripcionComercialServiceTest {

    @Mock
    private DescripcionComercialRepository descripcionComercialRepository;
    @Mock
    private JuegoClient juegoClient;
    @InjectMocks
    private DescripcionComercialService descripcionComercialService;

    private final Faker faker = new Faker();

    @Test
    void obtenerPorIdRetornaLaDescripcionComercial() {
        Long id = faker.number().numberBetween(1L, 1000L);
        String titulo = faker.lorem().sentence(3);
        DescripcionComercial descripcion = new DescripcionComercial(
                id, 20L, titulo, faker.lorem().paragraph(), faker.lorem().sentence(),
                LocalDateTime.now(), null
        );
        when(descripcionComercialRepository.findById(id)).thenReturn(Optional.of(descripcion));

        Optional<DescripcionComercialResponseDTO> resultado = descripcionComercialService.obtenerPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(titulo, resultado.get().tituloComercial());
        assertEquals(20L, resultado.get().juegoId());
    }

    @Test
    void eliminarLanzaExcepcionCuandoLaDescripcionNoExiste() {
        Long id = faker.number().numberBetween(1L, 1000L);
        when(descripcionComercialRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> descripcionComercialService.eliminar(id));
        verify(descripcionComercialRepository, never()).deleteById(id);
    }
}
