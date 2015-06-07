package com.appsfaculdade.metodosordenadores;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MergeSort extends MetodoOrdenador {
	
	@Override
	public String getName() {
		return "MergeSort";
	}
	
	@Override
	public void start() {
		this.setInicio(new Date());
		merge(this.vetor, 0, this.comprimentoVetor - 1);
		this.setFim(new Date());
	}

	public void merge(int x[], int inicio, int fim) {
		int meio;
		if (inicio < fim) {
			meio = (inicio + fim) / 2;
			merge(x, inicio, meio);
			merge(x, meio + 1, fim);
			intercala(x, inicio, fim, meio);
		}
	}

	public void intercala(int x[], int inicio, int fim, int meio) {
		int poslivre = inicio, iniciovetor1 = inicio, iniciovetor2 = meio + 1, i;
		int aux[] = new int[this.comprimentoVetor];
		while (iniciovetor1 <= meio && iniciovetor2 <= fim) {
			this.addComparacao();
			if (x[iniciovetor1] <= x[iniciovetor2]) {
				aux[poslivre] = x[iniciovetor1];
				iniciovetor1++;
			} else {
				aux[poslivre] = x[iniciovetor2];
				iniciovetor2++;
			}
			poslivre++;
		}
		for (i = iniciovetor1; i <= meio; i++) {
			aux[poslivre] = x[i];
			poslivre++;
		}
		for (i = iniciovetor2; i <= fim; i++) {
			aux[poslivre] = x[i];
			poslivre++;
		}
		for (i = inicio; i <= fim; i++) {
			this.addTroca();
			x[i] = aux[i];
		}
	}

}
