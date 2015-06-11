package com.aeroporto.webservice;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class AeroportoWebService {

	@WebMethod(operationName = "consultarVagas")
	public ArrayList<AssentosDisponiveisVoo> consultaVagas(@WebParam(name = "dataSaidaInicio") String dtSaidaInicio, @WebParam(name = "dataSaidaFim") String dtSaidaFim) {
		return new ArrayList<>();
	}
	
}