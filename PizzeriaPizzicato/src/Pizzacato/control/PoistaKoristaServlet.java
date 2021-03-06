package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/poistaKorista")
public class PoistaKoristaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/*
	 * 
	 * 	1. Poista korista parametrinš annetun id:n omaava tuote
	 * 	
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("pizza_id");
		String nimi = request.getParameter("nimi");
		if(poistaKorista(id, request.getSession())){
			request.getSession(false).setAttribute("notification", nimi + " poistettiin korista!");
		}
		response.sendRedirect("Ostoskori");
	}


	public Pizza haePizza(String id){
		Pizza pizza = null;
		PizzaDAO pizzadao = new PizzaDAO();
		try{
			pizza = pizzadao.haePizza(id);
			return pizza;
		}catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean poistaKorista(String id, HttpSession session){
			HashMap<String, Pizza> ostoskori = (HashMap<String, Pizza>) session.getAttribute("ostoskori");
	
			for(String key : ostoskori.keySet()){
				if(key.equals(id)){
					ostoskori.remove(key);
					break;
				}
			}
			session.setAttribute("ostoskori", ostoskori);
			return true;
	}
	
}
