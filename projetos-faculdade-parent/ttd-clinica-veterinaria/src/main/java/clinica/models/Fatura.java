package clinica.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Fatura {

	private List<ItemFatura> itens = new ArrayList<>();
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	public Recibo recibo;
	
	public void add( final ItemFatura item ){
		itens.add( item );
	}
	
	public Recibo efetuarPagamento( final FormaPagamento formaPagamento ){
		if ( this.recibo != null )
			throw new IllegalArgumentException( "Fatura já está quitado, não é possível pagar 2 vezes!" );
		this.formaPagamento = formaPagamento;
		this.recibo = new Recibo();
		this.recibo.setCliente( this.cliente );
		this.recibo.setFatura( this );
		return this.recibo;
	}
	
	public double total(){
		double valor = 0D;
		for ( final ItemFatura item : itens )
			valor += item.getValor();
		return valor;
	}
	
}
