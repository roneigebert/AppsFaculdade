package clinica.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;

@Data
@EqualsAndHashCode( callSuper=true )
public class Sociedade extends Cliente{

	final List<Socio> socios;
	
	public Sociedade( final String nome, final List<Socio> socios ) {
		super(nome);
		double total = 0D;
		for ( val socio : socios )
			total += socio.getCota();
		if ( total != 100 ) 
			throw new IllegalArgumentException( "Total das cotas deve ser igual a 100!" );
		this.socios = socios;
	}

	@Override
	public List<PartePagamento> gerarPartesPagamento( final Fatura fatura ) {
		final List<PartePagamento> partes = new ArrayList<>();
		for ( final Socio socio : socios )
			partes.add( new PartePagamento(socio, fatura.total() * socio.getCota() / 100) );
		return partes;
	}
	
}
