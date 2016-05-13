package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Pizzacato.model.Mauste;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.MausteDAO;
import Pizzacato.model.dao.TayteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Fantasia")
public class FantasiaMuokkausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/*
	 * 
	 * 1. Hae täytteet, jotta asiakas voi valita täytteistä mieluiset pizzaansa.
	 * 2. Hae mausteet, jotta asiakas voi valita täytteistä mieluiset pizzaansa.
	 * 3. Ohjaa asiakas Fantasia.jsp sivulle.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sivu = "/view/Fantasia.jsp";
		
		ArrayList<Tayte> taytteet = haeTaytteet();
		request.setAttribute("taytteet", taytteet);
			
		ArrayList<Mauste> mausteet = haeMausteet();
		request.setAttribute("mausteet", mausteet);
		RequestDispatcher dp = getServletContext().getRequestDispatcher(sivu);
		dp.forward(request, response);
	}
	
	public ArrayList<Tayte> haeTaytteet() {
		ArrayList<Tayte> taytteet = new ArrayList<>();
		try {
			taytteet = new TayteDAO().haeTaytteet();
			Collections.sort(taytteet);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return taytteet;
	}
	
	public ArrayList<Mauste> haeMausteet() {
		ArrayList<Mauste> mausteet = new ArrayList<>();
		try {
			mausteet = new MausteDAO().haeMausteet();
			Collections.sort(mausteet);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return mausteet;
	}



}
