package com.huellitas.vetturno.dto;

public record MascotaResponse(Long id, String nombre, String especie, String raza,
                              Integer edad, Long propietarioId, String propietarioNombre) {
}
