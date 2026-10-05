package com.huellitas.vetturno.dto;

import java.time.LocalDateTime;

public record CitaResponse(Long id, LocalDateTime fechaHora, String motivo,
                           Long mascotaId, String mascotaNombre,
                           Long veterinarioId, String veterinarioNombre) {
}
