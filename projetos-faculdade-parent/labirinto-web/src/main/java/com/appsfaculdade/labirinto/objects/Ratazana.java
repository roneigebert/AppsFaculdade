package com.appsfaculdade.labirinto.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ratazana {
	
	private int linha;
	private int coluna;
	private int linhaAnterior = Posicao.INDEFINIDA;;
	private int colunaAnterior = Posicao.INDEFINIDA;;
	private boolean achouSaida = false;
	private boolean morrer = false;
	private int queijosComidos = 0;
	
	public void comerQueijo(){
		this.queijosComidos += 1;
	}

	public void comemorar() {
		achouSaida = true;
	}

	public void morrer() {
		morrer = true;
	}
	
}
