package feature.clinica;

import org.junit.Assert;

import clinica.models.Cliente;
import clinica.models.Fatura;
import clinica.models.FormaPagamento;
import clinica.models.ItemFatura;
import clinica.models.Recibo;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ExameRotinaSteps {

	Cliente cliente;
	Fatura fatura;
	
	@Given("(.*) traz seu Doberman de estimação chamado Fofo na clínica para exames de rotina")
	public void entrarClinica( String nomeCliente ){
		cliente = new Cliente();
		cliente.setNome( nomeCliente );
	}
	
	@Then("O veterinário cobra dele a (.*) e a (.*), que custam (\\d+) e (\\d+) reais, respectivamente.")
	public void cobrarConsultaEVacina( String nomeItem1, String nomeItem2, double valorItem1, double valorItem2  )
	{
		fatura = new Fatura();
		fatura.setCliente( cliente );
		fatura.add( criarItem(nomeItem1, valorItem1) );
		fatura.add( criarItem(nomeItem2, valorItem2) );
		Assert.assertEquals(valorItem1 + valorItem2, fatura.total(), 0);
	}
	
	private ItemFatura criarItem( String descricao, double valor ){
		final ItemFatura item = new ItemFatura();
		item.setDescricao( descricao );
		item.setValor( valor );
		return item;
	}
	
	@Then("Dave paga em (.*) antes de ele sair para o trabalho e lhe é fornecido um recibo de pagamento para os serviços.")
	public void pagaEmDinheiroERecebeRecibo( final String formaPagamentoStr ){
		final FormaPagamento formaPagamento = FormaPagamento.from(formaPagamentoStr);
		final Recibo recibo = fatura.efetuarPagamento( formaPagamento );
		Assert.assertNotNull( recibo );
		Assert.assertEquals( cliente, recibo.getCliente() );
		Assert.assertEquals( fatura, recibo.getFatura() );
		Assert.assertEquals( fatura.getFormaPagamento().name().toLowerCase(), formaPagamentoStr.toLowerCase() );
	}
	
}
