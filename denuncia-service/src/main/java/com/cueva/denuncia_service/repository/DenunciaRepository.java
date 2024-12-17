package com.cueva.denuncia_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cueva.denuncia_service.entity.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
	public Denuncia findByDni(String dni);
}
