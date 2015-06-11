package com.aeroporto.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "voo")
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Voo {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="companhia_id")
	private Companhia companhia;
	
	@Column(name="aeroporto_saida_id")
	private Aeroporto aeroportoSaida;
	
	@Column(name="aeroporto_destino_id")
	private Aeroporto aeroportoDestino;
	
	@Column(name="data_saida")
	@XmlSchemaType(name="date")
	private Date dataSaida;
	
	@Column(name="data_chegada")
	@XmlSchemaType(name="date")
	private Date dataChegada;
	
	@Column(name="aviao_id")
	private Aviao aviao;
	
	@Column(name="valor_passagem")
	private Double valorPassagem;
	
}
