package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Pizza pizza = haePizza(id);
		poistaKorista(pizza, request.getSession());
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
	public void poistaKorista(Pizza pizza, HttpSession session){
		if(pizza != null){
			ArrayList<Pizza> ostoskori = (ArrayList<Pizza>) session.getAttribute("ostoskori");
			ostoskori.remove(pizza);
		}
	}
	
}
