package com.appsfaculdade.labirinto.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.servlet.http.Part;

import com.appsfaculdade.labirinto.exceptions.LabirintoException;
import com.appsfaculdade.labirinto.objects.Labirinto;
import com.appsfaculdade.labirinto.objects.Posicao;
import com.appsfaculdade.labirinto.objects.Ratazana;

import lombok.val;

public class LeitorLabirinto {
	
	public static Labirinto lerLabirinto(final Part filePart) throws IOException, LabirintoException {
		try {
			val labirinto = new Labirinto();
			labirinto.setRatazana(new Ratazana());
			int comprimentoLinha = -1;
			final BufferedReader bufferReader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF-8"));
			int linhas = 0;
			String linha = bufferReader.readLine();
			String linhaProxima = "";
			val posicaoesLidas = new LinkedList<LinkedList<Posicao>>();
			while (linha != null) {
				val listaLinha = new LinkedList<Posicao>();
				char posicoes[] = linha.toCharArray();
				linhaProxima = bufferReader.readLine();

				if (linhas == 0) {
					comprimentoLinha = posicoes.length;
				} else if (comprimentoLinha != posicoes.length) {
					bufferReader.close();
					throw new LabirintoException("Quantidade de colunas inválidas,");
				}
				for (int coluna = 0; coluna < posicoes.length; coluna++) {
					int tipo = Posicao.tipoPosicao(posicoes[coluna]);
					if (tipo == Posicao.INDEFINIDA) {
						bufferReader.close();
						return null;
					} else if (tipo == Posicao.POSICAO_SAIDA) {
						labirinto.getRatazana().setColuna(coluna);
						labirinto.getRatazana().setLinha(linhas);
					}
					listaLinha.add(coluna, new Posicao(tipo));
				}
				posicaoesLidas.add(linhas, listaLinha);
				linhas++;
				linha = linhaProxima;
			}
			bufferReader.close();
			if (posicaoesLidas.size() < 1)
				return null;
			labirinto.setPosicoes(posicaoesLidas);
			return labirinto;
		} catch (LabirintoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new LabirintoException("Arquivo deve estar inválido ou o caminho nao foi informado corretamente.", e);
		}
		return null;
	}
	
}