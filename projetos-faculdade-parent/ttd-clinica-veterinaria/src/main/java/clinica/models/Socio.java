package clinica.models;

import lombok.Getter;


public class Socio extends Cliente {

	@Getter
	final Double cota;
	
	public Socio( final String nome, final Double cota ) {
		super( nome );
		this.cota = cota;
	}

}
