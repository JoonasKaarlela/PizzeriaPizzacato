package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.PizzaDAO;

/**
 * Marianne K.
 */
@WebServlet("/piilota")
public class PiilotapizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
		
		String piilotettavan_id = request.getParameter("id");
		
		PizzaDAO pizzadao = new PizzaDAO();
		pizzadao.piilotaListalla(piilotettavan_id);
		
	} catch (SQLException e) {
		
		System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
				
	}
		response.sendRedirect("Menu");	

}
}