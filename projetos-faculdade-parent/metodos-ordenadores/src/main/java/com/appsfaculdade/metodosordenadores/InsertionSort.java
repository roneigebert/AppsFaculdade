package com.appsfaculdade.metodosordenadores;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InsertionSort extends MetodoOrdenador {

	@Override
	public String getName() {
		return "InsertionSort";
	}
	
	@Override
	public void start() {
		int i, j, eleito;
		this.setInicio(new Date());
		for (i = 1; i < this.comprimentoVetor; i++) {
			this.addComparacao();
			eleito = this.vetor[i];
			j = i - 1;
			while (j >= 0 && this.vetor[j] > eleito) {
				this.addTroca();
				this.vetor[j + 1] = this.vetor[j];
				j = j - 1;
			}
			this.vetor[j + 1] = eleito;
		}
		this.setFim(new Date());
	}

}
