package com.aeroporto.webservice;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.aeroporto.models.AssentoAviao;
import com.aeroporto.models.Voo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class AssentosDisponiveisVoo {
	
	private Voo voo;
	
	private ArrayList<AssentoAviao> assentosDisponiveis;
	
}
