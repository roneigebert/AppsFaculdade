package com.appsfaculdade.metodosordenadores;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SelectionSort extends MetodoOrdenador{

	@Override
	public String getName() {
		return "SelectionSort";
	}
	
	@Override
	public void start() {
		int i, j, eleito, menor, pos;
		this.setInicio(new Date());
		for (i = 0; i < this.comprimentoVetor-1; i++) {
			eleito = this.vetor[i];
			menor = this.vetor[i + 1];
			pos = i + 1;
			for (j = i + 2; j < this.comprimentoVetor; j++) {
				this.addComparacao();
				if (this.vetor[j] < menor) {
					this.addTroca();
					menor = this.vetor[j];
					pos = j;
				}
			}
			if (menor < eleito) {
				this.addTroca();
				this.vetor[i] = this.vetor[pos];
				this.vetor[pos] = eleito;
			}
		}
		this.setFim(new Date());
	}

}
