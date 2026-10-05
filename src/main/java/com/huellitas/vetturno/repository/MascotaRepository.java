package com.huellitas.vetturno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huellitas.vetturno.entity.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
