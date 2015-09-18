package com.cafeteira;

public class Creme extends CafeDecorator {

	public Creme(Cafe cafe) {
		super(cafe);
	}
	
	@Override
	public void preparar() {
		System.out.println("Preparando café com creme");
		super.preparar();
	}

	@Override
	public int getPreco() {
		return 1 + super.getPreco();
	}

}
