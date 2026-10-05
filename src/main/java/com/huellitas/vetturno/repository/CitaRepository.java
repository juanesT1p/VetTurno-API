package com.huellitas.vetturno.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huellitas.vetturno.entity.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByVeterinarioIdAndFechaHora(Long veterinarioId, LocalDateTime fechaHora);

    List<Cita> findByVeterinarioId(Long veterinarioId);
}
