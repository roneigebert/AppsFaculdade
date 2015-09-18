package com.cafeteira;

public class Preto extends Cafe {

	@Override
	public void preparar() {
		System.out.println("Preparando café pretinho");
	}

	@Override
	public int getPreco() {
		return 3;
	}

}
