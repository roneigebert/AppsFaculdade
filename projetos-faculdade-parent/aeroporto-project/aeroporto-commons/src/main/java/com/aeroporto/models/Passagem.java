package com.aeroporto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passagem")
@Getter
@Setter
public class Passagem {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="assento_aviao_id")
	private AssentoAviao assento;
	
	@Column(name="esta_pago")
	private Boolean estaPago;
	
	@Column(name="voo_id")
	private Voo voo;
	
	@Column(name="cliente_id")
	private Cliente cliente;
	
}
