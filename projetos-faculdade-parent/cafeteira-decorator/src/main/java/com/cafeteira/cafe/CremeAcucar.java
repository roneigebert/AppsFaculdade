package com.cafeteira.cafe;

public class CremeAcucar extends CafeDecorator {

	public CremeAcucar( final Cafe cafe ) {
		super( new Creme( new Acucar(cafe) ) );
	}

}
