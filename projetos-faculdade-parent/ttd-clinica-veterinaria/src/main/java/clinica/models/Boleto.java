package clinica.models;

import java.util.Date;

import lombok.Data;

@Data
public class Boleto {

	private Date dataVencimento;
	private Date dataPagamento;
	private double total;
	private Cliente cliente;
	private Fatura fatura;
	
	public static Boleto from( final Fatura fatura, final Date dataVencimento ) {
		final Boleto boleto = new Boleto();
		boleto.setDataVencimento( dataVencimento );
		boleto.setTotal( fatura.total() );
		boleto.setCliente( fatura.getCliente() );
		boleto.setFatura( fatura );
		return boleto;
	}

	public void quitar() {
		this.dataPagamento = new Date();
		fatura.quitar( FormaPagamento.BOLETO );
	}
	
}
