package clinica.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PartePagamento {

	final Cliente cliente;
	final Double partePagar;
	
}
