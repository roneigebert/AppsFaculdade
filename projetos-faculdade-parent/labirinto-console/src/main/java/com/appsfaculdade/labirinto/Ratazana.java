package com.appsfaculdade.labirinto;

import lombok.Getter;
import lombok.Setter;

import com.appsfaculdade.labirinto.utils.Utils;

@Getter
@Setter
public class Ratazana {
	
	private int linha;
	private int coluna;
	private int queijosComidos;
	private final String nome;
	
	public Ratazana(String nome) {
		this.nome = nome;
		this.queijosComidos = 0;
	}
	
	public void comerQueijo(){
		Audio.playSound("arroto.wav");
		Utils.sleep(800);
		this.queijosComidos += 1;
	}

	public String getNome() {
		return nome;
	}

	public int getQueijosComidos() {
		return queijosComidos;
	}

	public void comemorar() {
		System.out.println("\n\nACHOU!!! PARABENS!!!");
		Audio.playSound("comemoracao.wav");
		Utils.sleep(3500);
	}

	public void morrer() {
		System.out.println("\nGAME OVER");
		Audio.playSound("game_over.wav");
		Utils.sleep(3500);
	}

}
