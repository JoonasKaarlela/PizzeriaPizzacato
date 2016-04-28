package Pizzacato.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MuokkaaTilausta")
public class MuokkaaTilaustaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("tilaus_id");
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		String tilausaika = request.getParameter("tilausaika");
		String tila = request.getParameter("tila");
		double hinta = Double.parseDouble(request.getParameter("hinta"));
	
		// TODO: tallenna
		
	}

}
