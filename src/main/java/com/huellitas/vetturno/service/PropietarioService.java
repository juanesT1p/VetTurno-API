package com.huellitas.vetturno.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huellitas.vetturno.dto.PropietarioRequest;
import com.huellitas.vetturno.dto.PropietarioResponse;
import com.huellitas.vetturno.entity.Propietario;
import com.huellitas.vetturno.repository.PropietarioRepository;

@Service
public class PropietarioService {

    private final PropietarioRepository repository;

    public PropietarioService(PropietarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PropietarioResponse crear(PropietarioRequest request) {
        Propietario propietario = new Propietario();
        propietario.setNombre(request.getNombre());
        propietario.setDocumento(request.getDocumento());
        propietario.setTelefono(request.getTelefono());
        propietario.setEmail(request.getEmail());
        return toResponse(repository.save(propietario));
    }

    @Transactional(readOnly = true)
    public List<PropietarioResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private PropietarioResponse toResponse(Propietario propietario) {
        return new PropietarioResponse(propietario.getId(), propietario.getNombre(),
                propietario.getDocumento(), propietario.getTelefono(), propietario.getEmail());
    }
}
