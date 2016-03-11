package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Pizza;
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
		String tayte_id = request.getParameter("tayte_id");
		String kuvaus = request.getParameter("kuvaus");
	
		String listallaSTRING = request.getParameter("listalla");
		Boolean listalla = false;
		if(listallaSTRING != null){
			listalla = true;
		}
	
		Double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "pizza1.png";
		
		PizzaDAO pizzadao = new PizzaDAO();
		
		Pizza pizza = new Pizza(id, nimi, tayte_id, kuvaus, listalla, hinta, kuva);
		
		try{
			pizzadao.muokkaaPizzaa(pizza);
		} catch(SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
		response.sendRedirect("menu");
		
	}
}
