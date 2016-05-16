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

import Pizzacato.model.Mauste;
import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.MausteDAO;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;


@WebServlet("/ValitsePizza")
public class ValitsePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/*
	 * 	1. Hae pizza id:n perusteella
	 * 	2. Ohjaa asiakas Valitse.jsp sivulle. Jossa asiakas voi p‰‰tt‰‰ pizzojen m‰‰r‰n ja mausteet ja lis‰t‰ koriin.
	 * 	
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sivu = "/view/Valitse.jsp";
		String id = request.getParameter("id");
		
		try{
			
			Pizza pizza = new PizzaDAO().haePizza(id);
			request.setAttribute("pizza", pizza);
			
			if(pizza.getTaytteet().isEmpty()){ 
				
				ArrayList<Tayte> taytteet = (ArrayList<Tayte>) new TayteDAO().haeTaytteet();
				Collections.sort(taytteet);
				request.setAttribute("taytteet", taytteet);
				
				sivu = "/view/Fantasia.jsp"; 
			}
			
			ArrayList<Mauste> mausteet = (ArrayList<Mauste>) new MausteDAO().haeMausteet();
			Collections.sort(mausteet);
			
			request.setAttribute("mausteet", mausteet);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(sivu);
		rd.forward(request, response);
	}


}
