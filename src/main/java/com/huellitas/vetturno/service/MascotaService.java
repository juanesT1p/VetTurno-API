package com.huellitas.vetturno.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huellitas.vetturno.dto.MascotaRequest;
import com.huellitas.vetturno.dto.MascotaResponse;
import com.huellitas.vetturno.entity.Mascota;
import com.huellitas.vetturno.entity.Propietario;
import com.huellitas.vetturno.repository.MascotaRepository;
import com.huellitas.vetturno.repository.PropietarioRepository;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaService(MascotaRepository mascotaRepository,
                          PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    @Transactional
    public MascotaResponse crear(MascotaRequest request) {
        Propietario propietario = propietarioRepository.findById(request.getPropietarioId())
                .orElseThrow(() -> new IllegalArgumentException("El propietario no existe"));

        Mascota mascota = new Mascota();
        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setEdad(request.getEdad());
        mascota.setPropietario(propietario);
        return toResponse(mascotaRepository.save(mascota));
    }

    @Transactional(readOnly = true)
    public List<MascotaResponse> listar() {
        return mascotaRepository.findAll().stream().map(this::toResponse).toList();
    }

    private MascotaResponse toResponse(Mascota mascota) {
        Propietario propietario = mascota.getPropietario();
        return new MascotaResponse(mascota.getId(), mascota.getNombre(), mascota.getEspecie(),
                mascota.getRaza(), mascota.getEdad(), propietario.getId(), propietario.getNombre());
    }
}
