package clinica.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;


@AllArgsConstructor
public class Cliente {

	@Getter
	final String nome;
	final List<Cliente> c = new ArrayList<>();
	
	public final List<Boleto> gerarBoleto( final Fatura fatura, final Date dataVencimento ){
		val partes = gerarPartesPagamento(fatura);
		val boletos = new ArrayList<Boleto>();
		for ( val parte : partes )
			boletos.add( Boleto.from(fatura, parte.getPartePagar(), parte.getCliente(), dataVencimento) );
		return boletos;
	}

	public List<PartePagamento> gerarPartesPagamento( Fatura fatura ) {
		return Arrays.asList( new PartePagamento(this, fatura.total()) );
	}

}
