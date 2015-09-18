package com.cafeteira;


public class Pimenta extends CafeDecorator {

	public Pimenta(Cafe cafe) {
		super(cafe);
	}

	@Override
	public void preparar() {
		System.out.println("Preparando café com primenta");
		super.preparar();
	}
	
	@Override
	public int getPreco() {
		return 5 + super.getPreco();
	}

}
