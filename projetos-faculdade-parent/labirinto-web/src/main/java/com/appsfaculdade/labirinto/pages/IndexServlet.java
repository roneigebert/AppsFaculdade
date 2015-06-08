package com.appsfaculdade.labirinto.pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.appsfaculdade.labirinto.objects.Labirinto;
import com.appsfaculdade.labirinto.utils.LeitorLabirinto;

import lombok.val;

@MultipartConfig
@WebServlet(description = "Index Servlet", urlPatterns = { "" }, initParams = {})
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 2428112930973536918L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		val session = request.getSession();
		String error = null;
		Labirinto labirinto = null;
		try {
			val filePart = request.getPart("file"); 
			labirinto = LeitorLabirinto.lerLabirinto(filePart);
			session.setAttribute("labirinto", labirinto);
		} catch (final Exception e) {
			error = e.toString();
		}
		if (error == null && labirinto != null) {
			response.sendRedirect("game.jsp");
		} else {
			error = (labirinto == null) ? "Informe o labirinto" : error;
			val dispatcher = getServletContext().getRequestDispatcher("/index.html");
			val out = response.getWriter();
			out.println("<font color=red>" + error + "</font>");
			dispatcher.include(request, response);
			log("end");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.getRequestDispatcher("/index.html").forward(request, resp);
	}

}
