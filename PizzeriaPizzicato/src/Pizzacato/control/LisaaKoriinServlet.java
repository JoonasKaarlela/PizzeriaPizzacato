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
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/lisaaKoriin")
public class LisaaKoriinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("pizza_id");
		Pizza pizza = haePizza(id);
		
		if(request.getParameterValues("taytteet") != null){
			ArrayList<Tayte> lisaTaytteet = haeTaytteet(request.getParameterValues("taytteet"));
			pizza.getTaytteet().addAll(lisaTaytteet);
		}

		lisaaKoriin(pizza, request.getSession());
		
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
	
	public ArrayList<Tayte> haeTaytteet(String[] taytteet){
		ArrayList<Tayte> pizzan_taytteet = new ArrayList<>();
		TayteDAO taytedao = new TayteDAO();
		try{
			ArrayList<Tayte> kaikki_taytteet = taytedao.haeTaytteet();
			for(Tayte tayte : kaikki_taytteet){
				for(String tayte_id : taytteet){
					if(tayte.getTayte_id().equals(tayte_id)){
						pizzan_taytteet.add(tayte);
					}
				}
			}
		} catch( SQLException e){
			System.out.println(e.getMessage());
		}
		return pizzan_taytteet;
	}
	
	@SuppressWarnings("unchecked")
	public void lisaaKoriin(Pizza pizza, HttpSession session){
		if(pizza != null){
			if(session.getAttribute("ostoskori") == null){
				ArrayList<Pizza> ostoskori = new ArrayList<>();
				ostoskori.add(pizza);
				session.setAttribute("ostoskori", ostoskori);
			} else {
				ArrayList<Pizza> ostoskori = (ArrayList<Pizza>) session.getAttribute("ostoskori");
				ostoskori.add(pizza);
				session.setAttribute("ostoskori", ostoskori);
			}
		}
	}
	
}
