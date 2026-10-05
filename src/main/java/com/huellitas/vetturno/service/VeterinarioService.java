package com.huellitas.vetturno.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huellitas.vetturno.dto.VeterinarioRequest;
import com.huellitas.vetturno.dto.VeterinarioResponse;
import com.huellitas.vetturno.entity.Veterinario;
import com.huellitas.vetturno.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

    private final VeterinarioRepository repository;

    public VeterinarioService(VeterinarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public VeterinarioResponse crear(VeterinarioRequest request) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(request.getNombre());
        veterinario.setEspecialidad(request.getEspecialidad());
        veterinario.setRegistroProfesional(request.getRegistroProfesional());
        return toResponse(repository.save(veterinario));
    }

    @Transactional(readOnly = true)
    public List<VeterinarioResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private VeterinarioResponse toResponse(Veterinario veterinario) {
        return new VeterinarioResponse(veterinario.getId(), veterinario.getNombre(),
                veterinario.getEspecialidad(), veterinario.getRegistroProfesional());
    }
}
