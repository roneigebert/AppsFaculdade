package com.appsfaculdade.metodosordenadores;

import java.io.IOException;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GerarRelatorio {

	private static final int ORDENADO = 1;
	private static final int INVERTIDO = 2;
	private static final int DESORDENADO = 3;
	
	private static final int vetorOrdenado[] = geraVetor(new int[10000], ORDENADO);
	private static final int vetorDesordenado[] = geraVetor(new int[10000], DESORDENADO);
	private static final int vetorInvertido[] = geraVetor(new int[10000], INVERTIDO);
	
	private final Excel arquivo;
	
	public GerarRelatorio(final String outputDir) throws RowsExceededException, WriteException, IOException {
		this.arquivo = new Excel(outputDir);
	}

	public void gerarRelatorio() throws IOException, BiffException, WriteException {
		gerarResultados(BubbleSort.class);
		gerarResultados(MergeSort.class);
		gerarResultados(QuickSort.class);
		gerarResultados(SelectionSort.class);
		gerarResultados(InsertionSort.class);
		arquivo.save();
	}
	
	private void gerarResultados(final Class<? extends MetodoOrdenador> clazz) throws BiffException, WriteException, IOException{
		geraResultado(createInstance(clazz), ORDENADO);
		geraResultado(createInstance(clazz), INVERTIDO);
		geraResultado(createInstance(clazz), DESORDENADO);
	}
	
	private void geraResultado(final MetodoOrdenador metodoOrdenador, final int tipoVetor) throws BiffException, WriteException, IOException{
		int[] vetor = null;
		String tipo = null;
		switch (tipoVetor) {
		case ORDENADO:
			vetor = vetorOrdenado;
			tipo = "Ordenado";
		case INVERTIDO:
			vetor = vetorInvertido;
			tipo = "Invertido";
		case DESORDENADO:
			vetor = vetorDesordenado;
			tipo = "Desordenado";
		}
		metodoOrdenador.setVetor(vetor.clone());
		metodoOrdenador.start();
		System.out.println("\n\nMétodo " + metodoOrdenador.getName() + " " + tipo);
		metodoOrdenador.relatorio();
		String[] dadosRelatorio = metodoOrdenador.dadosRelatorio();
		arquivo.write("Método " + metodoOrdenador.getName()  + " " + tipo, dadosRelatorio[0], dadosRelatorio[1], dadosRelatorio[2]);
	}
	
	private MetodoOrdenador createInstance(final Class<? extends MetodoOrdenador> clazz){
		try {
			return clazz.newInstance();
		} catch (Exception e){
			throw new RuntimeException("Erro desconhecido", e);
		}
	}

	private static int[] geraVetor(final int[] vetor, final int tipoVetor) {
		int i, j=0;
		switch (tipoVetor) {
		case ORDENADO:
			for (i = 0; i < vetor.length; i++)
				vetor[i] = i + 1;
			return vetor;
		case INVERTIDO:
			for (i = (vetor.length - 1); i >= 0; i--)
				vetor[i] = j++;
			return vetor;
		case DESORDENADO:
			for (i = 0; i < vetor.length; i++) 
				vetor[i] = (int) (10000 * Math.random());
			return vetor;
		default:
			throw new IllegalArgumentException("Tipo vetor inválido");
		}
	}

}
