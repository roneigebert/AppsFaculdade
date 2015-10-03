package com.cafeteira;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cafeteira.GeradorTroco.MoedasInsuficientesException;
import com.cafeteira.GeradorTroco.SemMoedaDisponivelException;
import com.cafeteira.moedas.Moeda;
import com.cafeteira.moedas.Moeda10;
import com.cafeteira.moedas.Moeda100;
import com.cafeteira.moedas.Moeda25;
import com.cafeteira.moedas.Moeda5;
import com.cafeteira.moedas.Moeda50;

public class GeradorTrocoTest {

	private GeradorTroco geradorTroco;
	
	@Before
	public void before(){
		geradorTroco = new GeradorTroco( new FornecedorCompleto() );
	}
	
	@Test
	public void testTrocoSimples1() throws MoedasInsuficientesException, SemMoedaDisponivelException{
		geradorTroco.add( new Moeda100() );
		final List<Moeda> troco = geradorTroco.getTroco( 75 );
		Assert.assertEquals( 1, troco.size() );
		Assert.assertEquals( new Float(25F), troco.get(0).valor() );
	}
	
	@Test
	public void testTrocoSimples2() throws MoedasInsuficientesException, SemMoedaDisponivelException{
		geradorTroco.add( new Moeda100() );
		final List<Moeda> troco = geradorTroco.getTroco( 5 );
		Assert.assertEquals( 4, troco.size() );
		Assert.assertEquals( new Float(50f), troco.get(0).valor() );
		Assert.assertEquals( new Float(25f), troco.get(1).valor() );
		Assert.assertEquals( new Float(10f), troco.get(2).valor() );
		Assert.assertEquals( new Float(10f), troco.get(3).valor() );
	}
	
	@Test( expected=MoedasInsuficientesException.class )
	public void testTrocoInvalido() throws MoedasInsuficientesException, SemMoedaDisponivelException{
		geradorTroco.add( new Moeda100() );
		geradorTroco.getTroco( 110 );
	}
	
	@Test
	public void testNaoPrecisaDeTroco() throws MoedasInsuficientesException, SemMoedaDisponivelException{
		geradorTroco.add( new Moeda10() );
		geradorTroco.add( new Moeda25() );
		final List<Moeda> troco = geradorTroco.getTroco( 35 );
		Assert.assertTrue( troco.isEmpty() );
	}
	
	@Test( expected=SemMoedaDisponivelException.class )
	public void testMoedaIndisponivel() throws MoedasInsuficientesException, SemMoedaDisponivelException{
		final GeradorTroco geradorTrocoCustom = new GeradorTroco( new FornecedorMoedasNaoDisponiveis() );
		geradorTrocoCustom.add( new Moeda10() );
		geradorTrocoCustom.getTroco( 5 );
	}
	
	
	class FornecedorCompleto implements FornecedorMoedas{

		@Override
		public List<Moeda> moedasDisponiveis() {
			final List<Moeda> moedasEmbaracladas = new ArrayList<>();
			moedasEmbaracladas.add( new Moeda5() );
			moedasEmbaracladas.add( new Moeda25() );
			moedasEmbaracladas.add( new Moeda10() );
			moedasEmbaracladas.add( new Moeda100() );
			moedasEmbaracladas.add( new Moeda50() );
			return moedasEmbaracladas;
		}

		@Override
		public Moeda buscarMoeda( final Class<? extends Moeda> tipoDeMoeda ) throws SemMoedaDisponivelException{
			try {
				return tipoDeMoeda.newInstance();
			} catch ( Exception e ){
				throw new RuntimeException( "Erro desconhecido", e );
			}
			
		}
		
	}
	
	class FornecedorMoedasNaoDisponiveis extends FornecedorCompleto {
		
		@Override
		public Moeda buscarMoeda(Class<? extends Moeda> tipoDeMoeda)  throws SemMoedaDisponivelException {
			throw new SemMoedaDisponivelException();
		}
		
	}
	
}
