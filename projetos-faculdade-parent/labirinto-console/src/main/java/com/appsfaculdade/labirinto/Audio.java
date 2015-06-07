package com.appsfaculdade.labirinto;
import java.io.File;

import javax.sound.sampled.AudioSystem;

import lombok.val;

public class Audio {
	
	public static void playSound(final String url) {
		try {
			val clip = AudioSystem.getClip();
			val inputStream = AudioSystem.getAudioInputStream(new File("data/" + url));
			clip.open(inputStream);
			clip.start();
		} catch (final Exception e) {
			System.err.println("Erro ao reproduzir audio: " + e.getMessage());
		}
	}

}
