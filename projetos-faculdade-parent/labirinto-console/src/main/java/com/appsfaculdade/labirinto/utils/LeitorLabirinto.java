package com.appsfaculdade.labirinto.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import lombok.Cleanup;
import lombok.val;

import com.appsfaculdade.labirinto.Labirinto;
import com.appsfaculdade.labirinto.Posicao;
import com.appsfaculdade.labirinto.Ratazana;
import com.appsfaculdade.labirinto.exeptions.LabirintoException;

public class LeitorLabirinto {
	
	public static Labirinto lerLabirinto(final File file, final Ratazana ratazana) throws IOException, LabirintoException {
		try {
			final Labirinto labirinto = new Labirinto();
			labirinto.setRatazana(ratazana);
			int comprimentoLinha = -1;
			@Cleanup val arquivo = new FileReader(file);
			@Cleanup val arquivoReader = new BufferedReader(arquivo);
			int linhas = 0;
			String linha = arquivoReader.readLine();
			String linhaProxima = "";
			val posicaoesLidas = new LinkedList<LinkedList<Posicao>>();
			while (linha != null) {
				val listaLinha = new LinkedList<Posicao>();
				char posicoes[] = linha.toCharArray();
				linhaProxima = arquivoReader.readLine();
				if (linhas == 0) {
					comprimentoLinha = posicoes.length;
				} else if (comprimentoLinha != posicoes.length) {
					arquivoReader.close();
					throw new LabirintoException("Quantidade de colunas inválidas");
				}
				for (int coluna = 0; coluna < posicoes.length; coluna++) {
					int tipo = Posicao.tipoPosicao(posicoes[coluna]);
					if (tipo == Posicao.INDEFINIDA) {
						arquivoReader.close();
						return null;
					}else if (tipo == Posicao.POSICAO_SAIDA) {					
						labirinto.getRatazana().setColuna(coluna);
						labirinto.getRatazana().setLinha(linhas);	
					}	
					listaLinha.add(coluna, new Posicao(tipo));
				}
				posicaoesLidas.add(linhas, listaLinha);
				linhas++;
				linha = linhaProxima;
			}
			labirinto.setPosicoes(posicaoesLidas);
			return labirinto;
		} catch (final LabirintoException e){
			e.printStackTrace(); 
		} catch (final Exception e){
			throw new LabirintoException("Arquivo deve estar inválido ou o caminho nao foi informado corretamente.", e);
		}
		return null;
	}

}
