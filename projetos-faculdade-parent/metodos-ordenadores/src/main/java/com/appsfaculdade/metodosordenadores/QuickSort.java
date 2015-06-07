package com.appsfaculdade.metodosordenadores;

import java.util.Date;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuickSort extends MetodoOrdenador {

	@Override
	public String getName() {
		return "QuickSort";
	}
	
	@Override
	public void start() {
		this.setInicio(new Date());
		quicksort(this.vetor, 0, (this.comprimentoVetor - 1));
		this.setFim(new Date());
	}

	public void quicksort(int vet[], int ini, int fim) {
		int meio;
		this.addComparacao();
		if (ini < fim) {
			meio = partition(vet, ini, fim);
			quicksort(vet, ini, meio);
			quicksort(vet, meio + 1, fim);
		}
	}

	public int partition(int vet[], int ini, int fim) {
		int pivo, topo, i;
		pivo = vet[ini];
		topo = ini;
		for (i = ini + 1; i <= fim; i++) {
			this.addComparacao();
			if (vet[i] < pivo) {
				this.addTroca();
				vet[topo] = vet[i];
				vet[i] = vet[topo + 1];
				topo++;
			}
		}
		vet[topo] = pivo;
		return topo;
	}

}
