package Pizzacato.control;
import Pizzacato.model.dao.MausteDAO;
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

import Pizzacato.model.Mauste;
import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;

@WebServlet("/lisaaKoriin")
public class LisaaKoriinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("pizza_id");
		int maara = Integer.parseInt(request.getParameter("maara"));
		ArrayList<Mauste> mausteet = haeMausteet(request.getParameterValues("mausteet"));

		Pizza pizza = haePizza(id);
		pizza.setMausteet(mausteet);

		for (int i = 0; i < maara; i++) {
			if(lisaaKoriin(pizza, request.getSession())){
				String prefix = "";
				if(maara > 1){ prefix += maara + "x "; }
				request.getSession(false).setAttribute("notification", prefix + pizza.getNimi() + " lisättiin koriin!" );
			}
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
	
	public ArrayList<Mauste> haeMausteet(String[] valitut_mausteet){
		ArrayList<Mauste> mausteet = new ArrayList<>();
		if(valitut_mausteet != null){
			try{
				ArrayList<Mauste> kaikki_mausteet = new MausteDAO().haeMausteet();
				for(Mauste x : kaikki_mausteet){
					for(String y : valitut_mausteet){
						if(x.getMauste_id().equals(y)){
							mausteet.add(x);
						}
					}
				}
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		return mausteet;
	}
	
	@SuppressWarnings("unchecked")
	public boolean lisaaKoriin(Pizza pizza, HttpSession session){
		if(pizza != null){
			if(session.getAttribute("ostoskori") == null){
				HashMap<String, ArrayList<Pizza>> ostoskori = new HashMap<>();				
				ArrayList<Pizza> pizzat = new ArrayList<>();
				pizzat.add(pizza);
				ostoskori.put(pizza.getPizza_id(), pizzat);			
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
			return true;
		}
		return false;
	}
	
}
