package com.cafeteira;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cafeteira.GeradorTroco.MoedasInsuficientesException;
import com.cafeteira.GeradorTroco.SemMoedaDisponivelException;
import com.cafeteira.cafe.Acucar;
import com.cafeteira.cafe.Cafe;
import com.cafeteira.cafe.Creme;
import com.cafeteira.cafe.Preto;
import com.cafeteira.moedas.Moeda;

public class GebertsCoffeMachine implements CoffeeMachine {
	
	private GeradorTroco geradorTroco = new GeradorTroco( new FornecedorMoedasCafeteira() ); 
	private List<Moeda> ultimoTroco = new ArrayList<>();
	
	@Override
	public String cafePreto() {
		return criarCafe( new Preto(), "Cafe Preto" );
	}

	@Override
	public String cafeComCreme() {
		return criarCafe( new Creme(new Preto()), "Cafe com Creme" );
	}

	@Override
	public String cafeComAcucar() {
		return criarCafe( new Acucar(new Preto()), "Cafe com Acucar" );
	}

	@Override
	public String cafeComCremeAcucar() {
		return criarCafe( new Creme(new Acucar(new Preto())), "Cafe com Creme e Acucar" );
	}
	
	private String criarCafe( final Cafe cafe, final String cafeDescricao ) {
		try {
			ultimoTroco = geradorTroco.getTroco( cafe.getPreco() );
			geradorTroco = new GeradorTroco( new FornecedorMoedasCafeteira() );
			return cafeDescricao;
		} catch ( MoedasInsuficientesException e ) {
			return "Quantidade de moedas depositada insuficiente!";
		} catch ( SemMoedaDisponivelException e) {
			throw new RuntimeException( e );
		}
	}
	
	@Override
	public float depositarMoeda( final Moeda moeda ) {
		geradorTroco.add( moeda );
		return geradorTroco.getTotalDepositado();
	}

	@Override
	public Iterator<Moeda> getTroco() {
		final List<Moeda> listaRetorno = new ArrayList<>();
		listaRetorno.addAll( ultimoTroco );
		ultimoTroco.clear();
		return listaRetorno.iterator();
	}

}
