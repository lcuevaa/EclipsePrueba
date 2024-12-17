package com.cueva.denuncia_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cueva.denuncia_service.entity.Denuncia;
import com.cueva.denuncia_service.exception.GeneralServiceException;
import com.cueva.denuncia_service.exception.NoDataFoundException;
import com.cueva.denuncia_service.exception.ValidateServiceException;
import com.cueva.denuncia_service.repository.DenunciaRepository;
import com.cueva.denuncia_service.service.DenunciaService;
import com.cueva.denuncia_service.validator.DenunciaValidator;


@Service
public class DenunciaServiceImpl implements DenunciaService  {

	@Autowired
	private DenunciaRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Denuncia> findAll(Pageable page) {
		try {
			List<Denuncia> registros = repository.findAll(page).toList();
			return registros;
		}catch(ValidateServiceException | NoDataFoundException e){
			throw e;
		}catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Denuncia findByDni(String dni) {
		try {
			Denuncia registro=repository.findByDni(dni);
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			throw e;
		}catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Denuncia findById(int id) {
		try {
			Denuncia registro=repository.findById(id).orElseThrow(
					()->new NoDataFoundException("No existe un registro con ese nombre"));
			return registro;
		}catch(ValidateServiceException | NoDataFoundException e){
			throw e;
		}catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	@Override
	public Denuncia save(Denuncia obj) {
		try {
			//Registra
			DenunciaValidator.save(obj);
			if(obj.getId()==0) {
				Denuncia registroNuevo=repository.save(obj);
				return registroNuevo;
			}
			//Actualiza
			Denuncia registroExiste=findById(obj.getId());
			registroExiste.setDni(obj.getDni());
			registroExiste.setFecha(obj.getFecha()!= null
					? obj.getFecha()
					: registroExiste.getFecha());
			registroExiste.setTitulo(obj.getTitulo());
			registroExiste.setDireccion(obj.getDireccion());
			registroExiste.setDescripcion(obj.getDescripcion());
			
			return repository.save(registroExiste);
		}catch(ValidateServiceException | NoDataFoundException e){
			throw e;
		}catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			Denuncia registro=findById(id);
			repository.delete(registro);
			return true;
		}catch(ValidateServiceException | NoDataFoundException e){
			throw e;
		}catch(Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
}
