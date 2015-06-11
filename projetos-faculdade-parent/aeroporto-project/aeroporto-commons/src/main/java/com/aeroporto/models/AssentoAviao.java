package com.aeroporto.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assento_aviao")
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class AssentoAviao {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="identificacao")
	private String identificacao;
	
	@Column(name="detalhes")
	private String detalhes;
	
	@Column(name="modelo_aviao")
	private ModeloAviao modeloAviao;
	
}
