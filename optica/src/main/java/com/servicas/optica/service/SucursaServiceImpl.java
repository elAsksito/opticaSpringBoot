package com.servicas.optica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.servicas.optica.exceptions.ResourcesNotFoundException;
import com.servicas.optica.model.SucursalModel;
import com.servicas.optica.repository.SucursalRepository;

@Service
public class SucursaServiceImpl implements SucursalService {

	@Autowired
	SucursalRepository sucursalRepository;

	@Override
	public List<SucursalModel> obtenerTodasLasSucursales() {
		return sucursalRepository.findAll();
	}

	@Override
	public ResponseEntity<SucursalModel> obtenerSucursalPorId(int id) {
		Optional<SucursalModel> optionalSucursal = sucursalRepository.findById(id);
		if (optionalSucursal.isPresent()) {

			return ResponseEntity.ok(optionalSucursal.get());
		} else {
			throw new ResourcesNotFoundException("Sucursal no encontrada con el ID: " + id);
		}
	}

	@Override
	public ResponseEntity<SucursalModel> crearSucursal(SucursalModel sucursal) {
		SucursalModel nuevaSucursal = sucursalRepository.save(sucursal);
		return new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<SucursalModel> actualizarSucursal(int id, SucursalModel sucursalActualizada) {
		Optional<SucursalModel> optionalSucursal = sucursalRepository.findById(id);
		if (optionalSucursal.isPresent()) {
			SucursalModel sucursalExistente = optionalSucursal.get();
			sucursalExistente.setNombreSucursal(sucursalActualizada.getNombreSucursal());
			sucursalExistente.setDireccionSucursal(sucursalActualizada.getDireccionSucursal());
			sucursalExistente.setTelefonoSucursal(sucursalActualizada.getTelefonoSucursal());
			sucursalExistente.setCorreoSucursal(sucursalActualizada.getCorreoSucursal());
			SucursalModel sucursalActualizadaGuardada = sucursalRepository.save(sucursalExistente);

			return ResponseEntity.ok(sucursalActualizadaGuardada);
		} else {
			throw new ResourcesNotFoundException(
					"No se puede actualizar la sucursal. Sucursal no encontrada con el ID: " + id);
		}
	}

	@Override
	public void eliminarSucursal(int id) {
		Optional<SucursalModel> optionalSucursal = sucursalRepository.findById(id);
		if (optionalSucursal.isPresent()) {
			sucursalRepository.delete(optionalSucursal.get());
		} else {
			throw new ResourcesNotFoundException(
					"No se puede eliminar la sucursal. Sucursal no encontrada con el ID: " + id);
		}
	}

}
