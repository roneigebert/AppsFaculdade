package com.cafeteira;

import java.util.List;

import com.cafeteira.GeradorTroco.SemMoedaDisponivelException;
import com.cafeteira.moedas.Moeda;

public interface FornecedorMoedas {

	List<Moeda> moedasDisponiveis();
	Moeda buscarMoeda( final Class<? extends Moeda> tipoDeMoeda ) throws SemMoedaDisponivelException;
	
}
