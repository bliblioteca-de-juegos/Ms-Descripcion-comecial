package com.biblioteca.descripcioncomercial.repository;

import com.biblioteca.descripcioncomercial.model.DescripcionComercial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DescripcionComercialRepository extends JpaRepository<DescripcionComercial, Long> {

    Optional<DescripcionComercial> findByJuegoId(Long juegoId);

    boolean existsByJuegoId(Long juegoId);
}
