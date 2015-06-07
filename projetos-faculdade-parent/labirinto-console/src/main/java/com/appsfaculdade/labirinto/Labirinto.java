package com.appsfaculdade.labirinto;
import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

import com.appsfaculdade.labirinto.utils.Utils;

@Getter
@Setter
public class Labirinto {

	private LinkedList<LinkedList<Posicao>> posicoes;
	private Ratazana ratazana;
	
	public void start(){
		proximaPosicao(this.getRatazana().getLinha(), this.getRatazana().getColuna(), Posicao.INDEFINIDA, Posicao.INDEFINIDA);	
	}
	
	private void proximaPosicao(int linhaAtual, int colunaAtual, int linhaAnterior, int colunaAnterior){
		Utils.sleep(300);
		this.getRatazana().setLinha(linhaAtual);
		this.getRatazana().setColuna(colunaAtual); 
		if (this.get(linhaAtual, colunaAtual).getPassagens() >= numeroSaidas(linhaAtual, colunaAtual))
			this.posicoes.get(linhaAtual).get(colunaAtual).setSemSaida(true);
		this.posicoes.get(linhaAtual).get(colunaAtual).addPassagem();
		imprimirLabirinto();
		if (this.get(linhaAtual, colunaAtual).getSelf() == Posicao.COMIDA)
			this.comerQueijo(linhaAtual, colunaAtual);
		if (isSaida(linhaAtual, colunaAtual)){
			this.ratazana.comemorar();
		} else {
			int proximaLinhaColuna[] = proximaLinhaColuna(linhaAtual, colunaAtual, linhaAnterior, colunaAnterior);
			if (proximaLinhaColuna[0]== Posicao.INDEFINIDA)
				this.ratazana.morrer();
			else
				proximaPosicao(proximaLinhaColuna[0], proximaLinhaColuna[1], linhaAtual, colunaAtual);
		}
	}
	
	private int[] proximaLinhaColuna(int linhaAtual, int colunaAtual, int linhaAnterior, int colunaAnterior) {
		boolean podeIrAcima = this.podeIrAcima(linhaAtual, colunaAtual);
		boolean podeIrEsquerda = this.podeIrEsquerda(linhaAtual, colunaAtual);
		boolean podeIrAbaixo = this.podeIrAbaixo(linhaAtual, colunaAtual);
		boolean podeIrDireita = this.podeIrDireita(linhaAtual, colunaAtual);
		boolean subiu = (linhaAnterior == Posicao.INDEFINIDA || linhaAtual < linhaAnterior);
		boolean deceu = (linhaAtual > linhaAnterior);
		boolean foiDireita = (colunaAtual > colunaAnterior);
		boolean foiEsquera = (colunaAtual < colunaAnterior);
		if (subiu){
			//caso tenha subido
			if (podeIrEsquerda){
				return new int[]{linhaAtual, colunaAtual -1};
			}else if (podeIrAcima){
				return new int[]{linhaAtual -1, colunaAtual};
			}else if (podeIrDireita){
				return new int[]{linhaAtual, colunaAtual + 1};
			}else if (podeIrAbaixo){
				return new int[]{linhaAtual + 1, colunaAtual};
			}
		}else if(deceu){
			//caso tenha decido
			if (podeIrDireita){
				return new int[]{linhaAtual, colunaAtual + 1};
			}else if (podeIrAbaixo){
				return new int[]{linhaAtual + 1, colunaAtual};
			}else if (podeIrEsquerda){
				return new int[]{linhaAtual, colunaAtual -1};
			}else if (podeIrAcima){
				return new int[]{linhaAtual -1, colunaAtual};
			}
		}else if (foiDireita){
			//caso tenha ido para direita
			if (podeIrAcima){
				return new int[]{linhaAtual -1, colunaAtual};
			}else if (podeIrDireita){
				return new int[]{linhaAtual, colunaAtual + 1};
			}else if (podeIrAbaixo){
				return new int[]{linhaAtual + 1, colunaAtual};
			}else if (podeIrEsquerda){
				return new int[]{linhaAtual, colunaAtual -1};
			}
		}else if (foiEsquera){
			//caso tenha ido a esquerda
			if (podeIrAbaixo){
				return new int[]{linhaAtual + 1, colunaAtual};
			}else if (podeIrEsquerda){
				return new int[]{linhaAtual, colunaAtual -1};
			}else if (podeIrAcima){
				return new int[]{linhaAtual -1, colunaAtual};
			}else if (podeIrDireita){
				return new int[]{linhaAtual, colunaAtual + 1};
			}
		}
		return new int[]{Posicao.INDEFINIDA, Posicao.INDEFINIDA};
	}
	
	private void comerQueijo(final int linha, final int coluna){
		this.ratazana.comerQueijo();
		this.posicoes.get(linha).get(coluna).setSelf(Posicao.CAMINHO);
	}
	
	private Posicao get(final int linha, final int coluna) {
		return this.posicoes.get(linha).get(coluna);
	}

	public void imprimirLabirinto() {
		int linha = 0;
		int queijosComidos = this.getRatazana().getQueijosComidos();
		val nome = this.getRatazana().getNome();
		if (queijosComidos == 0)
			System.out.println("\n\n\n " + nome + " está com fome, não comeu nenhum queijo :(");
		else if (queijosComidos == 1)
			System.out.println("\n\n\n " + nome + " já comeu 1 queijo :)");
		else
			System.out.println("\n\n\n " + nome + " está enchendo a barriga, ja comeu " + queijosComidos + " queijos");
		final int ratoColuna = this.getRatazana().getColuna();
		final int ratoLinha = this.getRatazana().getLinha() ;
		for (final LinkedList<Posicao> listaLinha : this.posicoes) {
			System.out.println("");
			int coluna = 0;
			for (final Posicao posicao : listaLinha) {
				if (linha==ratoLinha && coluna == ratoColuna)
					System.out.print((queijosComidos < 3)? "M" : "R");
				else
					System.out.print(posicao.toString());	
				coluna ++;
			}
			linha++;
		}
	}
	
	private boolean isSaida(final int linha, final int coluna){
		return ((coluna == 0 || coluna == this.posicoes.get(0).size()-1) | (linha == 0 || linha == this.posicoes.size() -1)) ? true : false;
	}
	
	private boolean podeIrEsquerda(int linha, int coluna){
		if (coluna == 0){
			return false;
		}else if (this.get(linha, coluna -1 ).podeCaminhar()){
			return true;
		}
		return false;
	}
	
	private boolean podeIrDireita(final int linha, final int coluna){
		if (coluna > posicoes.get(0).size()-2)
			return false;
		else if (this.get(linha, coluna + 1).podeCaminhar())
			return true;
		return false;
	}
		
	private boolean podeIrAcima(final int linha, final int coluna){
		if (linha == 0)
			return false;
		else if (this.get(linha -1, coluna).podeCaminhar())
			return true;
		return false;
	}
	
	private boolean podeIrAbaixo(final int linha, final int coluna){
		if (linha >= posicoes.size() -1)
			return false;
		else if (this.get(linha + 1, coluna).podeCaminhar())
			return true;
		return false;
	}
	
	/**
	 * retorna o numero de saidas em que eh possiver caminhar que uma posicao pode ter..
	 */
	private int numeroSaidas(final int linha, final  int coluna){
		int saidas = 0;
		if (podeIrAbaixo(linha, coluna))
			saidas += 1;
		if (podeIrAcima(linha, coluna))
			saidas +=1;
		if (podeIrEsquerda(linha, coluna))
			saidas += 1;
		if (podeIrDireita(linha, coluna))
			saidas += 1;
		return saidas;	
	}

}
