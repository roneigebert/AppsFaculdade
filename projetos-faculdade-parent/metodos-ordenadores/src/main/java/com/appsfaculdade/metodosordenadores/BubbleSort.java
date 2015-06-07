package com.appsfaculdade.metodosordenadores;
import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BubbleSort extends MetodoOrdenador {

	@Override
	public String getName() {
		return "BubbleSort";
	}
	
	@Override
	public void start() {
		int n, i, aux, troca;
		n = 1;
		troca = 1;
		this.setInicio(new Date());
		while (n <= this.comprimentoVetor && troca == 1) {
			troca = 0;
			for (i = 0; i < this.comprimentoVetor - 1; i++) {
				this.addComparacao();
				if (this.vetor[i] > this.vetor[i + 1]) {
					troca = 1;
					aux = this.vetor[i];
					this.addTroca();
					this.vetor[i] = this.vetor[i + 1];
					this.vetor[i + 1] = aux;
				}
			}
			n = n + 1;
		}
		this.setFim(new Date());
	}

}
