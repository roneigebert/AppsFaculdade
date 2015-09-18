package com.cafeteira;

import org.junit.Assert;
import org.junit.Test;

public class CafeTest {

	@Test
	public void testCafe(){
		Cafe cafe = new Pimenta(new Creme( new Creme(new Preto())));
		cafe.preparar();
		Assert.assertEquals(10, cafe.getPreco());
	}
	
}
