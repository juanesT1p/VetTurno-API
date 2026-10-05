package com.huellitas.vetturno.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huellitas.vetturno.dto.CitaRequest;
import com.huellitas.vetturno.dto.CitaResponse;
import com.huellitas.vetturno.entity.Cita;
import com.huellitas.vetturno.entity.Mascota;
import com.huellitas.vetturno.entity.Veterinario;
import com.huellitas.vetturno.repository.CitaRepository;
import com.huellitas.vetturno.repository.MascotaRepository;
import com.huellitas.vetturno.repository.VeterinarioRepository;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(CitaRepository citaRepository, MascotaRepository mascotaRepository,
                       VeterinarioRepository veterinarioRepository) {
        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Transactional
    public CitaResponse crear(CitaRequest request) {
        if (request.getFechaHora() == null || !request.getFechaHora().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La cita debe tener una fecha futura");
        }

        Mascota mascota = mascotaRepository.findById(request.getMascotaId())
                .orElseThrow(() -> new IllegalArgumentException("La mascota no existe"));
        Veterinario veterinario = veterinarioRepository.findById(request.getVeterinarioId())
                .orElseThrow(() -> new IllegalArgumentException("El veterinario no existe"));

        if (citaRepository.existsByVeterinarioIdAndFechaHora(veterinario.getId(), request.getFechaHora())) {
            throw new IllegalArgumentException("El veterinario ya tiene una cita en esa fecha y hora");
        }

        Cita cita = new Cita();
        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        return toResponse(citaRepository.save(cita));
    }

    @Transactional(readOnly = true)
    public List<CitaResponse> listar() {
        return citaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<CitaResponse> listarPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId).stream()
                .map(this::toResponse).toList();
    }

    private CitaResponse toResponse(Cita cita) {
        return new CitaResponse(cita.getId(), cita.getFechaHora(), cita.getMotivo(),
                cita.getMascota().getId(), cita.getMascota().getNombre(),
                cita.getVeterinario().getId(), cita.getVeterinario().getNombre());
    }
}
