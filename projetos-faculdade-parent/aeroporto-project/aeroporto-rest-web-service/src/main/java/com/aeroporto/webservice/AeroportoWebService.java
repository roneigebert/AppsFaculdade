package com.aeroporto.webservice;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/consulta_vagas")
public class AeroportoWebService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultaVagas(@QueryParam("dataInicio") String dataInicio, @QueryParam("dataFinal") String dataFinal) {
		return Response.ok(new ArrayList<>()).build();
	}
	
}