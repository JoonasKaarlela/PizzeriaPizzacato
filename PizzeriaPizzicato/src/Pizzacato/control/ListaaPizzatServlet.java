package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;

/**
 * Matias K
 */
@WebServlet("/Menu")
public class ListaaPizzatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// Luodaan pizzadao ja haetaan kaikki pizzat
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = new ArrayList<>();
		try {
			pizzat = pizzadao.haePizzat();
		} catch (SQLException e) {
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			e.printStackTrace();
		}
		// Tallennetaan request-olion alle kaikki pizzat
		request.setAttribute("pizzat", pizzat);
		
		String sivu = "/view/Menu.jsp";
		
		try{
			// Haetaan käyttäjä sessiosta
			Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");		
			// Tsekataan onko käyttäjä omistaja vai asiakas
			if( kayttaja.isOmistaja() ){
				sivu = "/view/Omistaja.jsp";
			} 
			// Kaikki ok => Renderaa sivu
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(sivu);	
			dispather.forward(request, response);
			
		}catch(NullPointerException e){
			// Jos käyttäjä ei ole kirjautunut => renderaa Menu.jsp
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(sivu);	
			dispather.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
