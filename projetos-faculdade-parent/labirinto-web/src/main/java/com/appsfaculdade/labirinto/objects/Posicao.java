package com.appsfaculdade.labirinto.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posicao {

	public static final int INDEFINIDA = -1;
	public static final int NULA = 0;
	public static final int LABIRINTO = 1;
	public static final int CAMINHO = 2;
	public static final int COMIDA = 3;
	public static final int ARMADILHA = 4;
	public static final int POSICAO_SAIDA = 5;

	private int passagens;
	private boolean semSaida;
	private int self;

	/**
	 * 
	 * @param self
	 * 		a própria posicao
	 */
	public Posicao(final int self) {
		this.self = self;
		this.semSaida = false;
		this.passagens = 0;
	}
	
	public boolean podeCaminhar(){		
		return (this.semSaida) ? false : (self == CAMINHO || self == COMIDA || self == POSICAO_SAIDA);
	}

	@Override
	public String toString() {
		switch (this.self) {
		case Posicao.LABIRINTO:
			return "#";
		case Posicao.POSICAO_SAIDA:
			return ".";
		case Posicao.ARMADILHA:
			return "T";
		case Posicao.COMIDA:
			return "C";
		case Posicao.CAMINHO:
			return ".";
		default:
			return "";
		}
	}

	public static int tipoPosicao(char caracter) {
		switch (caracter) {
		case '.':
			return Posicao.CAMINHO;
		case '#':
			return Posicao.LABIRINTO;
		case 'S':
			return Posicao.POSICAO_SAIDA;
		case 'E':
			return Posicao.CAMINHO;
		case 'C':
			return Posicao.COMIDA;
		case 'T':
			return Posicao.ARMADILHA;
		default:
			return Posicao.INDEFINIDA;
		}
	}
	
	public void addPassagem() {
		this.passagens += 1;	
	}

}