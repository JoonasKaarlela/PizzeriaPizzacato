package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

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
		
		// Laita pizzat id järjestykseen id:n mukaan
		Collections.sort(pizzat);
		
		// Tallennetaan request-olion alle kaikki pizzat
		request.setAttribute("pizzat", pizzat);
		
		
		
		// Katso onko kirjautunut, omistaja/asiakas
		String sivu = "/view/Menu.jsp";
		
		try{
			// Nollaa errorit
			request.removeAttribute("error");
			
			// Haetaan käyttäjä sessiosta
			Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");	
			System.out.println("Tervetuloa " + kayttaja.getKayttajatunnus() + "!");
			
			// Haetaan mahdolliset errorit
			String error = (String) request.getSession().getAttribute("error");
			request.setAttribute("error", error);
			
			// Tsekataan onko käyttäjä omistaja vai asiakas
			if( kayttaja.isOmistaja() ){
				sivu = "/view/Omistaja.jsp";
				System.out.println("Olet omistaja");
			} else {
				System.out.println("Et ole omistaja");
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


}
