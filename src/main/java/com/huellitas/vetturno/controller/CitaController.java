package com.huellitas.vetturno.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.huellitas.vetturno.dto.CitaRequest;
import com.huellitas.vetturno.dto.CitaResponse;
import com.huellitas.vetturno.service.CitaService;

@RestController
@RequestMapping("/api/citas")
@SecurityRequirement(name = "bearerAuth")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(@Valid @RequestBody CitaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(request));
    }

    @GetMapping
    public List<CitaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/veterinario/{id}")
    public List<CitaResponse> listarPorVeterinario(@PathVariable Long id) {
        return service.listarPorVeterinario(id);
    }
}
