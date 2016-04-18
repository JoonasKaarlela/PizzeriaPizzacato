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
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/lisaaKoriin")
public class LisaaKoriinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("pizza_id");
		int maara = Integer.parseInt(request.getParameter("maara"));

		Pizza pizza = haePizza(id);

		for (int i = 0; i < maara; i++) {
			lisaaKoriin(pizza, request.getSession());
		}
		
		response.sendRedirect("Menu");
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
	public void lisaaKoriin(Pizza pizza, HttpSession session){
		if(pizza != null){
			if(session.getAttribute("ostoskori") == null){
				HashMap<String, ArrayList<Pizza>> ostoskori = new HashMap<>();
				ostoskori.put(pizza.getPizza_id(), new ArrayList<>());
				session.setAttribute("ostoskori", ostoskori);
			} else {
				HashMap<String, ArrayList<Pizza>> ostoskori = (HashMap<String, ArrayList<Pizza>>) session.getAttribute("ostoskori");
				boolean match = false;
				for(String id : ostoskori.keySet()){
					if(id.equals(pizza.getPizza_id())){
						ostoskori.get(id).add(pizza);
						match = true;
					}
				}
				if(!match){
					ArrayList<Pizza> uusi_tyyppi = new ArrayList<>();
					uusi_tyyppi.add(pizza);
					ostoskori.put(pizza.getPizza_id(), uusi_tyyppi);
				}
				session.setAttribute("ostoskori", ostoskori);
			}
		}
	}
	
}
