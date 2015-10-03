package com.cafeteira;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

import com.cafeteira.moedas.Moeda;

public class GeradorTroco {

	private final List<Moeda> moedasDisponiveis = new ArrayList<>();
	private final List<Moeda> moedas = new ArrayList<>();
	private final FornecedorMoedas fornecedorMoedas;
	@Getter
	private float totalDepositado = 0;
	
	public GeradorTroco( final FornecedorMoedas fornecedorMoedas ) {
		moedasDisponiveis.addAll( fornecedorMoedas.moedasDisponiveis() );
		Collections.sort(moedasDisponiveis, (a,b)-> Float.compare(b.valor(), a.valor()));
		this.fornecedorMoedas = fornecedorMoedas;
	}
	
	public void add( Moeda moeda ){
		totalDepositado += moeda.valor();
		moedas.add( moeda );
	}
	
	public List<Moeda> getTroco( final float total ) throws MoedasInsuficientesException, SemMoedaDisponivelException{
		if ( total > this.totalDepositado )
			throw new MoedasInsuficientesException();
		return gerarTroco( total );
	}
	
	private List<Moeda> gerarTroco( final float total ) throws SemMoedaDisponivelException{
		List<Moeda> moedas = new ArrayList<>();
		if ( this.totalDepositado == total )
			return new ArrayList<>();
		float troco = 0;
		do{
			final Moeda moedaTroco = maiorMoedaPara( this.totalDepositado - (troco + total) );
			troco += moedaTroco.valor();
			moedas.add( moedaTroco );
		} while ( troco + total < this.totalDepositado );
		if ( (troco + total) != this.totalDepositado )
			throw new RuntimeException( "Erro desconhecido ao gerar o troco" );
		return moedas;
	}
	
	private Moeda maiorMoedaPara( final float valor ) throws SemMoedaDisponivelException{
		for ( Moeda moedaDisponivel : moedasDisponiveis )
			if ( valor >= moedaDisponivel.valor() )
				return fornecedorMoedas.buscarMoeda( moedaDisponivel.getClass() );
		throw new RuntimeException( "Erro desconhecido" );
	}
	
	public static class MoedasInsuficientesException extends Exception{

		private static final long serialVersionUID = 6980376057425140512L;

		public MoedasInsuficientesException() {
			super( "Moedas depositadas são insificientes!" );
		}
		
	}
	
	public static class SemMoedaDisponivelException extends Exception{
		
		private static final long serialVersionUID = 6980376057425140512L;
		
		public SemMoedaDisponivelException() {
			super( "Não há moedas disponiveis no momento!" );
		}
		
	}
	
}
