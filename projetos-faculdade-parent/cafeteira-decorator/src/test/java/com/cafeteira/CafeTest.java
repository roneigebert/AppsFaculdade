package com.cafeteira;

import org.junit.Assert;
import org.junit.Test;

import com.cafeteira.cafe.Acucar;
import com.cafeteira.cafe.Cafe;
import com.cafeteira.cafe.Creme;
import com.cafeteira.cafe.Preto;

public class CafeTest {

	@Test
	public void testCafePretoComDuploCreme(){
		final Cafe cafe = new Creme(new Creme( new Preto()));
		cafe.preparar();
		Assert.assertEquals(375, cafe.getPreco());
	}
	
	@Test
	public void testCafe(){
		Cafe cafe = new Acucar(new Creme( new Creme(new Preto())));
		cafe.preparar();
		Assert.assertEquals(425, cafe.getPreco());
	}
	
}
