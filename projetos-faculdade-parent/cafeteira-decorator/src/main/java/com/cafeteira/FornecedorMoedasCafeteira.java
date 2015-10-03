package com.cafeteira;

import java.util.ArrayList;
import java.util.List;

import com.cafeteira.GeradorTroco.SemMoedaDisponivelException;
import com.cafeteira.moedas.Moeda;
import com.cafeteira.moedas.Moeda10;
import com.cafeteira.moedas.Moeda100;
import com.cafeteira.moedas.Moeda25;
import com.cafeteira.moedas.Moeda5;
import com.cafeteira.moedas.Moeda50;

public class FornecedorMoedasCafeteira implements FornecedorMoedas{

	@Override
	public List<Moeda> moedasDisponiveis() {
		final List<Moeda> moedasEmbaracladas = new ArrayList<>();
		moedasEmbaracladas.add( new Moeda5() );
		moedasEmbaracladas.add( new Moeda25() );
		moedasEmbaracladas.add( new Moeda10() );
		moedasEmbaracladas.add( new Moeda100() );
		moedasEmbaracladas.add( new Moeda50() );
		return moedasEmbaracladas;
	}

	@Override
	public Moeda buscarMoeda( final Class<? extends Moeda> tipoDeMoeda ) throws SemMoedaDisponivelException{
		try {
			return tipoDeMoeda.newInstance();
		} catch ( Exception e ){
			throw new RuntimeException( "Erro desconhecido", e );
		}
	}
	
}
