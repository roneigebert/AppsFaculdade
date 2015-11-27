package clinica.models;

import java.util.List;

import lombok.Data;

@Data
public class Fatura {

	private final List<ItemFatura> itens;
	private final Cliente cliente;
	private final List<PartePagamento> partes;
	
	private FormaPagamento formaPagamento;
	public Recibo recibo;
	
	public Fatura( final Cliente cliente, final List<ItemFatura> itens ) {
		this.itens = itens;
		this.cliente = cliente;
		this.partes = cliente.gerarPartesPagamento( this );
	}
	
	public Recibo quitar( final FormaPagamento formaPagamento ){
		if ( this.recibo != null )
			throw new IllegalArgumentException( "Fatura já está quitado, não é possível pagar 2 vezes!" );
		this.formaPagamento = formaPagamento;
		this.recibo = new Recibo();
		this.recibo.setCliente( this.cliente );
		this.recibo.setFatura( this );
		return this.recibo;
	}
	
	public boolean estaQuitada(){
		return recibo != null;
	}
	
	public double total(){
		double valor = 0D;
		for ( final ItemFatura item : itens )
			valor += item.getValor();
		return valor;
	}

}
