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
		return from(fatura, fatura.total(), fatura.getCliente(), dataVencimento);
	}
	
	public static Boleto from( final Fatura fatura, final Double valor, final Cliente cliente, final Date dataVencimento ) {
		final Boleto boleto = new Boleto();
		boleto.setDataVencimento( dataVencimento );
		boleto.setTotal( valor );
		boleto.setCliente( cliente );
		boleto.setFatura( fatura );
		return boleto;
	}

	public void quitar() {
		this.dataPagamento = new Date();
		fatura.quitar( FormaPagamento.BOLETO );
	}
	
}
