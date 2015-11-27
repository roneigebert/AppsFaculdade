package feature.clinica;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import clinica.models.Boleto;
import clinica.models.Cliente;
import clinica.models.Fatura;
import clinica.models.FormaPagamento;
import clinica.models.ItemFatura;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PagamentoBoletoSteps {

	Cliente cliente;
	Fatura fatura;
	Boleto boleto;
	
	@Given("(.*) traz seus dois gatos, Tweedle Dee e Tweedle Dum, para serem castrados.")
	public void entrarClinica( String nomeCliente ){
		cliente = new Cliente( nomeCliente );
	}
	
	@And("Dr. Roberts realiza as esterilizações \\(que tem uma taxa normal\\).")
	public void efetuarProcedimentoMedico(){
		fatura = new Fatura( cliente, Arrays.asList(new ItemFatura("Esterilizações", 50D)) );
		Assert.assertFalse( fatura.estaQuitada() );
	}
	
	@And("Traci pega seus gatos naquela noite, mas deseja pagar pelo serviço ao final do mês.")
	public void pagarFinalDoMes(){
		fatura.setFormaPagamento( FormaPagamento.BOLETO );
		boleto = Boleto.from( fatura, dataVencimento() );
		Assert.assertEquals( fatura.total(), boleto.getTotal(), 0 );
		Assert.assertEquals( boleto.getCliente(), cliente );
		Assert.assertNull( boleto.getDataPagamento() );
	}
	
	@Then("Naquele dia, ela recebe uma única fatura com os procedimentos detalhadas.")
	public void recebeFatura(){
		Assert.assertNotNull( fatura );
		Assert.assertEquals( cliente, fatura.getCliente() );
		Assert.assertEquals( FormaPagamento.BOLETO, fatura.getFormaPagamento() );
	}
	
	@And("No final do mês, ela recebe um boleto referente àquela conta, com o qual ela paga pelo serviço.")
	public void recebeBoleto(){
		boleto.quitar();
		Assert.assertNotNull( boleto.getDataPagamento() );
		Assert.assertTrue( fatura.estaQuitada() );
	}
	
	private Date dataVencimento(){
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
}
