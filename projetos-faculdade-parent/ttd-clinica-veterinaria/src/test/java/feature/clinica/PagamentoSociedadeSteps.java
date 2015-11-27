package feature.clinica;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.val;

import org.junit.Assert;

import clinica.models.Boleto;
import clinica.models.Fatura;
import clinica.models.ItemFatura;
import clinica.models.PartePagamento;
import clinica.models.Sociedade;
import clinica.models.Socio;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PagamentoSociedadeSteps {

	Sociedade sociedade;
	Socio socioQueTrazOAnimal;
	Fatura fatura;
	
	@Given("O cavalo de corrida \"(.*)\"")
	public void entrarClinica( final String nome ){
		System.out.println( "CAVALO ---------------------------------> "  + nome);
	}
	
	@And("é possuído pelas seguintes pessoas:")
	public void carregarListaSocios( List<Socio> socios ){
		this.sociedade = new Sociedade("Nome da empresa", socios);
	}
	
	@Then("O cavalo está começando a mostrar alguns sinais de claudicação, assim (.*) traz o cavalo em um exame")
	public void trazCavaloParaExame( String nomePessoa ){
		for ( val socio : sociedade.getSocios() )
			if ( socio.getNome().equalsIgnoreCase(nomePessoa) )
				socioQueTrazOAnimal = socio;
		Assert.assertNotNull( socioQueTrazOAnimal );
	}
	
	@Then("Dr. Roberts decide que não é motivo de preocupação e prescreve algum (.*) tópico.")
	public void criarFatura( String medicamento ){
		this.fatura = new Fatura( sociedade, Arrays.asList(new ItemFatura(medicamento, 200)) );
	}
	
	@Then("(.*) é fornecido com uma fatura discriminando as acusações")
	public void fatura( String nomePessoa ){
		Assert.assertEquals(socioQueTrazOAnimal.getNome(), nomePessoa);
	}
	
	@And("A fatura também mostra o percentual da conta que cada proprietário é responsável.")
	public void and(){
		Assert.assertEquals(5, fatura.getPartes().size());
		for ( val socio : sociedade.getSocios() ){
			PartePagamento parteSocio = null;
			for ( val parte : fatura.getPartes() ){
				if ( parte.getCliente().equals( socio ) )
					parteSocio = parte;
			}
			Assert.assertNotNull( parteSocio );
			Assert.assertEquals( fatura.total() * socio.getCota() / 100 , parteSocio.getPartePagar(), 0);
		}
	}
	
	@Then("No final do mês, um boleto é enviado para Grady, Martin, Ralph, Erich e Brian detalhando a quantidade que eles devem.")
	public void en(){
		val boletos = this.sociedade.gerarBoleto(fatura, new Date());
		Assert.assertEquals(5, boletos.size());
		for ( val socio : sociedade.getSocios() ){
			Boleto boletoSocio = null;
			for ( val boleto : boletos ){
				if ( boleto.getCliente().equals( socio ) )
					boletoSocio = boleto;
			}
			Assert.assertNotNull( boletoSocio );
			Assert.assertEquals( fatura.total() * socio.getCota() / 100 , boletoSocio.getTotal(), 0);
		}
	}
	
}
