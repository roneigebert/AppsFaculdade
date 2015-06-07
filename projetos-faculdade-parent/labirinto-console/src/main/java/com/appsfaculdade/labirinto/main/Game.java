package com.appsfaculdade.labirinto.main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import lombok.val;

import com.appsfaculdade.labirinto.Labirinto;
import com.appsfaculdade.labirinto.Ratazana;
import com.appsfaculdade.labirinto.exeptions.LabirintoException;
import com.appsfaculdade.labirinto.utils.LeitorLabirinto;


public class Game {

	private static Scanner consoleInput = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		new Game().start();
	}
	
	public void start() throws IOException, LabirintoException {
		val ratazana = getRatazana();
		val labirinto = getLabirinto(ratazana);
		labirinto.start();
	}

	private Labirinto getLabirinto(final Ratazana ratazana) {
		System.out.println("Informe o caminho do arquivo (Exemplo C:\\labirinto.txt) OU tecle enter..");
		val caminho = consoleInput.nextLine();
		try{
			val file = (caminho.equals("")) ? new File("data/labirinto.txt") : new File(caminho);	
			if (!file.getName().endsWith(".txt"))
				throw new LabirintoException("A extenção do arquivo deve ser .TXT, informe um arquivo válido!");
			val labirinto = LeitorLabirinto.lerLabirinto(file, ratazana);
			if (labirinto == null){
				System.out.println("ARQUIVO INVÁLIDO!!!");
				return getLabirinto(ratazana);
			}
			return labirinto;
		}catch (Exception e){
			e.printStackTrace();
			return getLabirinto(ratazana);
		}		
	}
	
	private Ratazana getRatazana(){
		System.out.println("Informe o nome da ratazana");
		val nome  = consoleInput.nextLine();
		if (StringUtils.isBlank(nome)){
			System.out.println("Informe um nome válido!!");
			return getRatazana();
		}else{
			return new Ratazana(nome);
		}
	}
	
}
