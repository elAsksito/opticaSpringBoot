package com.servicas.optica.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicas.optica.model.SucursalModel;
import com.servicas.optica.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {
	
	
	public final SucursalService sucursalService;
	
	public SucursalController(SucursalService sucursalService) {
		this.sucursalService = sucursalService;
	}
	
	@GetMapping("/")
	public List<SucursalModel> obtenerTodasLasSucursales() {
		return sucursalService.obtenerTodasLasSucursales();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SucursalModel> obtenerSucursalPorId(@PathVariable int id) {
		return sucursalService.obtenerSucursalPorId(id);
	}

	@PostMapping("/")
	public ResponseEntity<SucursalModel> crearSucursal(@RequestBody SucursalModel sucursal) {
		return sucursalService.crearSucursal(sucursal);
	}

	@PutMapping("/{id}")
	public ResponseEntity<SucursalModel> actualizarSucursal(@PathVariable int id, @RequestBody SucursalModel sucursalActualizada) {
		return sucursalService.actualizarSucursal(id, sucursalActualizada);
	}

	@DeleteMapping("/{id}")
	public void eliminarSucursal(@PathVariable int id) {
		sucursalService.eliminarSucursal(id);
	}

}
