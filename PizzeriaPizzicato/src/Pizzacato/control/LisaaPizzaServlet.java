package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/LisaaPizzaServlet")
public class LisaaPizzaServlet extends HttpServlet {
	

			

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pizza_id = request.getParameter("pizza_id");
		String nimi = request.getParameter("nimi");
		String tayte_id = request.getParameter("tayte_id");
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = false;
		double hinta = Double.valueOf("hinta");
		String kuva = request.getParameter("kuva");
	
		
		Pizza pizza = new Pizza(pizza_id, nimi, tayte_id, kuvaus, listalla, hinta, kuva);

		
		PizzaDAO dao = new PizzaDAO();
		
		try{
			dao.lisaaPizza(pizza);
		} catch(SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
		
		response.sendRedirect("menu");
}

	}


