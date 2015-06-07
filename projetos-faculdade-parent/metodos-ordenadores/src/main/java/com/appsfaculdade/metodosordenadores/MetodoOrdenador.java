package com.appsfaculdade.metodosordenadores;
import java.util.Date;

import lombok.Setter;

public abstract class MetodoOrdenador {
	
	protected int quantidadesComparacoes = 0;;
	protected int quantidadesTrocas = 0;
	protected int[] vetor;
	protected int comprimentoVetor;
	
	@Setter
	private Date inicio;
	@Setter
	private Date fim;
	
	abstract void start();
	
	abstract String getName();

	public void setVetor(final int[] vetor) {
		this.vetor = vetor;
		this.comprimentoVetor = vetor.length;
	}
	
	public void addComparacao(){
		this.quantidadesComparacoes += 1;
	}
	
	public  void addTroca(){
		this.quantidadesTrocas += 1;
	}

	public double segundosExecucao(){
		return ((double)fim.getTime() - (double)inicio.getTime())/1000;
	}
	
	public void imprimirVetor(){
		int contador = 1;
		for (int numero : this.vetor){
			System.out.println(contador+" - "+numero);
			contador++;
		}
	}
	
	public String[] dadosRelatorio(){
		return new String[]{quantidadesComparacoes+"", quantidadesTrocas+"", segundosExecucao()+" s."};
	}
	
	public void relatorio(){
		System.out.println("Número comparações: "+quantidadesComparacoes);
		System.out.println("Número trocas: "+quantidadesTrocas);
		System.out.println("Tempo execução: "+segundosExecucao()+" segundos\n");
	}
	
}
