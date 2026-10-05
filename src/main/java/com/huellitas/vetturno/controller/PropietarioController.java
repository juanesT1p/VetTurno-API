package com.huellitas.vetturno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.huellitas.vetturno.dto.PropietarioRequest;
import com.huellitas.vetturno.dto.PropietarioResponse;
import com.huellitas.vetturno.service.PropietarioService;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    private final PropietarioService service;

    public PropietarioController(PropietarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PropietarioResponse> crear(@Valid @RequestBody PropietarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping
    public List<PropietarioResponse> listar() {
        return service.listar();
    }
}
