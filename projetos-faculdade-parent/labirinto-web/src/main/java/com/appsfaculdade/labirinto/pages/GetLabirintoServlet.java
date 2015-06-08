package com.appsfaculdade.labirinto.pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsfaculdade.labirinto.objects.Labirinto;

import lombok.val;

@WebServlet(description = "Get Labirinto", urlPatterns = { "game/ajax/" }, initParams = {})
public class GetLabirintoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		val session = request.getSession();
		val labirinto = (Labirinto) session.getAttribute("labirinto");
		response.setContentType("application/json");
		val out = response.getWriter();
		if (request.getParameter("labirinto") == null) {
			labirinto.proximaPosicao();
			out.print(labirinto.labirintoPosicoesJson());
		} else {
			out.print(labirinto.labirintoJson());
		}
		out.flush();
		session.setAttribute("labirinto", labirinto);
	}

}
