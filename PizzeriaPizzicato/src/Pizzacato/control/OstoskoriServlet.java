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
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Ostoskori.jsp");
		
		// Laske yhteissumma
		HashMap<String, ArrayList<Pizza>> pizzat = (HashMap<String, ArrayList<Pizza>>) request.getSession().getAttribute("ostoskori");
		double summa = 0;
		
		if(pizzat != null){
			for(String key : pizzat.keySet()){
				for(Pizza pizza : pizzat.get(key)){
					summa += pizza.getHinta();
				}
			}
		}
		
		
		request.setAttribute("summa", summa);
		
		dp.forward(request, response);
	}
}
