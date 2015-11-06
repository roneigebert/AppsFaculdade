package clinica.models;

public enum FormaPagamento {

	DINHEIRO, CARTAO;
	
	public static FormaPagamento from( final String string ){
		for ( final FormaPagamento formaPagamento : values() )
			if ( formaPagamento.name().equalsIgnoreCase(string) )
				return formaPagamento;
		return null;
	}
	
}
