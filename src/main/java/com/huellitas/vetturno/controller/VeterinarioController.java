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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.huellitas.vetturno.dto.VeterinarioRequest;
import com.huellitas.vetturno.dto.VeterinarioResponse;
import com.huellitas.vetturno.service.VeterinarioService;

@RestController
@RequestMapping("/api/veterinarios")
@SecurityRequirement(name = "bearerAuth")
public class VeterinarioController {

    private final VeterinarioService service;

    public VeterinarioController(VeterinarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponse> crear(@Valid @RequestBody VeterinarioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping
    public List<VeterinarioResponse> listar() {
        return service.listar();
    }
}
