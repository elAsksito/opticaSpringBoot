package com.servicas.optica.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicas.optica.exceptions.ResourcesNotFoundException;
import com.servicas.optica.model.SucursalModel;
import com.servicas.optica.repository.SucursalRepository;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	SucursalRepository sucursalRepository;

	@GetMapping("/")
	public List<SucursalModel> obtenerTodasLasSucursales() {
		return sucursalRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SucursalModel> obtenerSucursalPorId(@PathVariable int id) {
		Optional<SucursalModel> optionalSucursal = sucursalRepository.findById(id);
		if (optionalSucursal.isPresent()) {
			return ResponseEntity.ok(optionalSucursal.get());
		} else {
			throw new ResourcesNotFoundException("Sucursal no encontrada con el ID: " + id);
		}
	}

	@PostMapping("/")
	public ResponseEntity<SucursalModel> crearSucursal(@RequestBody SucursalModel sucursal) {
		SucursalModel nuevaSucursal = sucursalRepository.save(sucursal);
		return new ResponseEntity<>(nuevaSucursal, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SucursalModel> actualizarSucursal(@PathVariable int id,
			@RequestBody SucursalModel sucursalActualizada) {
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

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarSucursal(@PathVariable int id) {
		Optional<SucursalModel> optionalSucursal = sucursalRepository.findById(id);
		if (optionalSucursal.isPresent()) {
			sucursalRepository.delete(optionalSucursal.get());
			return ResponseEntity.noContent().build();
		} else {
			throw new ResourcesNotFoundException(
					"No se puede eliminar la sucursal. Sucursal no encontrada con el ID: " + id);
		}
	}

	@ExceptionHandler(ResourcesNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFoundException(ResourcesNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

}
