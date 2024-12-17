package com.cueva.denuncia_service.validator;

import com.cueva.denuncia_service.entity.Denuncia;
import com.cueva.denuncia_service.exception.ValidateServiceException;

public class DenunciaValidator {
	public static void save(Denuncia obj) {
		if(obj.getTitulo() == null || obj.getTitulo().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre es requerido");
		}
		if(obj.getTitulo().length() >= 3) {
			throw new ValidateServiceException("El titulo no debe tener más de 3 caracteres");
		}
	}
}
