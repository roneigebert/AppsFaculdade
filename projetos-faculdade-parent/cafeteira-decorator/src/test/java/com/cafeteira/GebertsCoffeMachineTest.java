package com.cafeteira;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cafeteira.moedas.Moeda;
import com.cafeteira.moedas.Moeda10;
import com.cafeteira.moedas.Moeda100;
import com.cafeteira.moedas.Moeda5;
import com.cafeteira.moedas.Moeda50;

public class GebertsCoffeMachineTest {
    
    private GebertsCoffeMachine cafeteira;
    
    @Before
    public void before(){
    	cafeteira = new GebertsCoffeMachine();
    }
    
    @After
    public void after(){
    	final Iterator<Moeda> troco = cafeteira.getTroco();
    	Assert.assertFalse( troco.hasNext() );
    }
    
    @Test
    public void testCafePreto() {
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda10() );
    	cafeteira.depositarMoeda( new Moeda10() );
    	cafeteira.depositarMoeda( new Moeda50() );
    	cafeteira.depositarMoeda( new Moeda10() );
    	cafeteira.cafePreto();
    	final Iterator<Moeda> troco = cafeteira.getTroco();
    	Assert.assertTrue( troco.hasNext() );
    	Assert.assertEquals( new Float(5f), troco.next().valor() );
    	Assert.assertFalse( troco.hasNext() );
    }

    @Test
    public void testCafePretoComCreme() {
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.cafeComCreme();
    	final Iterator<Moeda> troco = cafeteira.getTroco();
    	Assert.assertTrue( troco.hasNext() );
    	Assert.assertEquals( new Float(25f), troco.next().valor() );
    	Assert.assertFalse( troco.hasNext() );
    }
    
    @Test
    public void testCafeComAcucar() {
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.cafeComAcucar();
    	final Iterator<Moeda> troco = cafeteira.getTroco();
    	Assert.assertTrue( troco.hasNext() );
    	Assert.assertEquals( new Float(50f), troco.next().valor() );
    	Assert.assertTrue( troco.hasNext() );
    	Assert.assertEquals( new Float(25f), troco.next().valor() );
    	Assert.assertFalse( troco.hasNext() );
    }
    
    @Test
    public void testCafeComCremeAndAcucar() {
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda100() );
    	cafeteira.depositarMoeda( new Moeda10() );
    	cafeteira.depositarMoeda( new Moeda10() );
    	cafeteira.depositarMoeda( new Moeda5() );
    	cafeteira.cafeComCremeAcucar();
    	final Iterator<Moeda> troco = cafeteira.getTroco();
    	Assert.assertFalse( troco.hasNext() );
    }
    
}
