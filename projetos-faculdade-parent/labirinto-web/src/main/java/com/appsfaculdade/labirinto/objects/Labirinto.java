package com.appsfaculdade.labirinto.objects;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;
import lombok.val;

public class Labirinto {

	private LinkedList<LinkedList<Posicao>> posicoes;
	
	@Getter
	@Setter
	public Ratazana ratazana;
	
	public void proximaPosicao(){
		int linhaAtual = ratazana.getLinha();
		int colunaAtual = ratazana.getColuna();
		int colunaAnterior = ratazana.getColunaAnterior();
		int linhaAnterior = ratazana.getLinhaAnterior(); 
		if (this.get(linhaAtual, colunaAtual).getPassagens() >= numeroSaidas(linhaAtual, colunaAtual))
			this.posicoes.get(linhaAtual).get(colunaAtual).setSemSaida(true);
		this.posicoes.get(linhaAtual).get(colunaAtual).addPassagem();
		if (this.get(linhaAtual, colunaAtual).getSelf() == Posicao.COMIDA)
			this.comerQueijo(linhaAtual, colunaAtual);
		if (isSaida(linhaAtual, colunaAtual)){
			this.ratazana.comemorar();
		}else{
			int proximaLinhaColuna[] = proximaLinhaColuna(linhaAtual, colunaAtual, linhaAnterior, colunaAnterior);
			if (proximaLinhaColuna[0]== Posicao.INDEFINIDA){
				this.ratazana.morrer();
			}else{
				ratazana.setLinha(proximaLinhaColuna[0]);
				ratazana.setColuna(proximaLinhaColuna[1]);
				ratazana.setLinhaAnterior(linhaAtual);
				ratazana.setColunaAnterior(colunaAtual);
			}
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

	public void setPosicoes(final LinkedList<LinkedList<Posicao>> posicoes) {
		this.posicoes = posicoes;
	}

	private boolean isSaida(final int linha, final int coluna){
		return ((coluna == 0 || coluna == this.posicoes.get(0).size()-1) | (linha == 0 || linha == this.posicoes.size() -1)) ? true : false;
	}
	
	private boolean podeIrEsquerda(final int linha, final int coluna){
		if (coluna == 0)
			return false;
		else if (this.get(linha, coluna -1 ).podeCaminhar())
			return true;
		return false;
	}
	
	private boolean podeIrDireita(int linha, int coluna){
		if (coluna > posicoes.get(0).size()-2){
			return false;
		}else if (this.get(linha, coluna + 1).podeCaminhar()){
			return true;
		}
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
	
	private int numeroSaidas(final int linha, final int coluna){
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
	
	public String labirintoJson(){
		val buffer = new StringBuilder("{\"labirinto\": [");
		int linha = 0;
		for (final LinkedList<Posicao> listaLinha : this.posicoes) {
			buffer.append("[");
			int coluna = 0;
			for (final Posicao posicao : listaLinha) {
				buffer.append("{\"self\": "+posicao.getSelf()+",");
				buffer.append("\"acima\":"+podeIrAcima(linha, coluna)+",");
				buffer.append("\"abaixo\":"+podeIrAbaixo(linha, coluna)+",");
				buffer.append("\"esquerda\":"+podeIrEsquerda(linha, coluna)+",");
				buffer.append("\"direita\":"+podeIrDireita(linha, coluna)+"},");
				coluna ++;
			}
			int index = buffer.lastIndexOf(",");
			buffer.replace(index, index+1, "");
			buffer.append("],");
			linha ++;
		}
		int index = buffer.lastIndexOf(",");
		buffer.replace(index, index+1, "");
		buffer.append("]}");
		return buffer.toString();
	}
	
	public String labirintoPosicoesJson(){
		val buffer = new StringBuilder("{\"linha\": "+ratazana.getLinha()+",");
		buffer.append("\"coluna\":"+ratazana.getColuna()+",");
		buffer.append("\"linhaAnterior\":"+ratazana.getLinhaAnterior()+",");
		buffer.append("\"colunaAnterior\":"+ratazana.getColunaAnterior()+",");
		buffer.append("\"queijosComidos\":"+ratazana.getQueijosComidos()+",");
		buffer.append("\"saida\": "+ratazana.isAchouSaida()+",");
		buffer.append("\"morrer\": "+ratazana.isMorrer()+"}");
		return buffer.toString();
	}

}
