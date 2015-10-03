package com.cafeteira.cafe;


public class Acucar extends CafeDecorator {

	public Acucar(Cafe cafe) {
		super(cafe);
	}

	@Override
	public void preparar() {
		System.out.println("Preparando café com acucar");
		super.preparar();
	}
	
	@Override
	public int getPreco() {
		return 50 + super.getPreco();
	}

}
