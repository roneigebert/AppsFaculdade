package com.cafeteira.cafe;

public class Creme extends CafeDecorator {

	public Creme(Cafe cafe) {
		super(cafe);
	}
	
	@Override
	public void preparar() {
		System.out.println("Preparando caf� com creme");
		super.preparar();
	}

	@Override
	public int getPreco() {
		return 100 + super.getPreco();
	}

}
