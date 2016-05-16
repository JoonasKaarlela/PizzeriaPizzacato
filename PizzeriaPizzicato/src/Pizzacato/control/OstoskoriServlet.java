package Pizzacato.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Pizza;

@WebServlet("/Ostoskori")
public class OstoskoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 
	 * 	1. Hae ostoskorin tuotteet kannasta
	 * 	2. Laske korin hinta
	 * 	3. Tsekkaa onko kori tyhjä vai ei
	 * 	4. Tallenna attribuutit
	 * 	5. Ohjaa asiakas ostoskori sivulle
	 * 
	 * 
	 */
	
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Ostoskori.jsp");

		HashMap<String, Pizza> pizzat = (HashMap<String, Pizza>) request.getSession().getAttribute("ostoskori");
		
		try{
		if(!pizzat.isEmpty()){	
			double summa = 0;
			for(String id : pizzat.keySet()){
				summa += pizzat.get(id).getHinta();
			}	
			request.setAttribute("summa", String.format("%.2f", summa));
		} else {
			request.setAttribute("tyhja", true);
		}
		
		
		}catch(NullPointerException e){
			request.setAttribute("tyhja", true);
		}
		
		dp.forward(request, response);
	}
}
