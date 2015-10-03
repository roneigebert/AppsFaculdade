package com.cafeteira;

import java.util.Iterator;

import com.cafeteira.moedas.Moeda;

public interface CoffeeMachine {
    
	String cafePreto();
    String cafeComCreme();
    String cafeComAcucar();
    String cafeComCremeAcucar();
    float depositarMoeda(Moeda moeda);
    Iterator<Moeda> getTroco();
   
}
