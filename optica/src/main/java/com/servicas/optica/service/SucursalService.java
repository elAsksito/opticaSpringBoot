package com.servicas.optica.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.servicas.optica.model.SucursalModel;

public interface SucursalService {
	
	List<SucursalModel> obtenerTodasLasSucursales();
	ResponseEntity<SucursalModel> obtenerSucursalPorId( int id);
	ResponseEntity<SucursalModel> crearSucursal( SucursalModel sucursal);
	ResponseEntity<SucursalModel> actualizarSucursal( int id,  SucursalModel sucursalActualizada);
	void eliminarSucursal( int id);
}
