package com.cafeteira;

import org.junit.Assert;
import org.junit.Test;

public class CafeTest {

	@Test
	public void testCafePretoComDuploCreme(){
		final Cafe cafe = new Creme(new Creme( new Preto()));
		cafe.preparar();
		Assert.assertEquals(5, cafe.getPreco());
	}
	
	@Test
	public void testCafe(){
		Cafe cafe = new Acucar(new Creme( new Creme(new Preto())));
		cafe.preparar();
		Assert.assertEquals(10, cafe.getPreco());
	}
	
}
