package com.cafeteira;

public class Preto extends Cafe {

	@Override
	public void preparar() {
		System.out.println("Preparando caf� pretinho");
	}

	@Override
	public int getPreco() {
		return 3;
	}

}
