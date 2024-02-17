package com.servicas.optica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="sucursal")
public class SucursalModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_sucursal")
	private int idSucursal;
	
	@Column(name="nombre_sucursal")
	private String nombreSucursal;
	
	@Column(name="direccion_sucursal")
	private String direccionSucursal;
	
	@Column(name="telefono_sucursal")
	private String telefonoSucursal;
	
	@Column(name="correo_sucursal")
	private String correoSucursal;
}
