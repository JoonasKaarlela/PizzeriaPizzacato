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

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;

/**
 * Marianne
 */
@WebServlet("/muokkaa")
public class MuokkaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String nimi = request.getParameter("nimi");
		ArrayList<Tayte> taytteet = new ArrayList<>(); // request.getParameter("taytteet");
		String kuvaus = request.getParameter("kuvaus");
	
		String listallaSTRING = request.getParameter("piilossa");
		Boolean listalla = false;
		if(listallaSTRING != null){
			listalla = true;
		}
	
		Double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "pizza1.png";
		
		PizzaDAO pizzadao = new PizzaDAO();
		
		Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
		
		try{
			pizzadao.muokkaaPizzaa(pizza);
			response.sendRedirect("Menu");
		} catch(SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}
