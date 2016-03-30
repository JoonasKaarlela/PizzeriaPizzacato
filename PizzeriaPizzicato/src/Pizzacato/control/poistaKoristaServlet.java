package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/poistaKorista")
public class poistaKoristaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String pizza_id = request.getParameter("id");
		Pizza pizza = new Pizza();
		
		PizzaDAO pizzadao = new PizzaDAO();
		try{
			pizza = pizzadao.haePizza(pizza_id);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		if(pizza != null){
			if(request.getSession(false).getAttribute("ostoskori") == null){
				System.out.println("Tehdään uusi ostoskori...");
				ArrayList<Pizza> ostoskori = new ArrayList<>();
				ostoskori.remove(pizza);
				request.getSession(false).setAttribute("ostoskori", ostoskori);
			} else {
				@SuppressWarnings("unchecked")
				ArrayList<Pizza> ostoskori = (ArrayList<Pizza>) request.getSession(false).getAttribute("ostoskori");
				ostoskori.remove(pizza);
				request.getSession(false).setAttribute("ostoskori", ostoskori);
			}
		} else {
			System.out.println("Pizzaa ei löytynyt");
		}
		
	}
}
