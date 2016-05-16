package Pizzacato.control;
import Pizzacato.model.dao.MausteDAO;
import Pizzacato.model.dao.TayteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pizzacato.model.Mauste;
import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.Utils;
import Pizzacato.model.dao.PizzaDAO;

@WebServlet("/lisaaKoriin")
public class LisaaKoriinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/*
	 *
	 * 	1. Hae pizzalle valitut mausteet tietokannasta
	 * 	2. Hae valittu pizza tietokannasta id:n perusteella.
	 * 	3. Talleta haetulle pizalle haetut mausteet
	 * 	4. Lisää koriin kyseinen pizza.	(HashMap<String, ArrayList<Pizza>, jossa string on pizzan id ja pizzat on kyseisen id:n omaavat pizzat) 
	 * 	5. Ohjaa asiakas Menuun
	 * 
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("pizza_id");
		int maara = Integer.parseInt(request.getParameter("maara"));
		ArrayList<Mauste> mausteet = haeMausteet(request.getParameterValues("mausteet"));
		ArrayList<Tayte> taytteet = haeTaytteet(request.getParameterValues("taytteet"));

		Pizza pizza = haePizza(id);
		
		System.out.println(pizza);

		if(request.getParameterValues("mausteet") != null || !mausteet.isEmpty()){
			pizza.setMausteet(mausteet);
		}
		
		double summa = pizza.getHinta();
		if(request.getParameterValues("taytteet") != null || !taytteet.isEmpty()){
			pizza.setTaytteet(taytteet);
			summa = 6.00;
			for(int x=0; x<taytteet.size(); x++){
				summa += 1.00;
			}
		}

		pizza.setHinta(summa);
			
		for (int i = 0; i < maara; i++) {
			@SuppressWarnings("unchecked")
			Map<String, Pizza> ostoskori = (HashMap<String, Pizza>) request.getSession().getAttribute("ostoskori");
			System.out.println(ostoskori);
			if(ostoskori == null){
				Map<String, Pizza> uusi_kori = new HashMap<String, Pizza>();
				request.getSession(true).setAttribute("ostoskori", uusi_kori);
			}
			ostoskori.put(new Utils().generate(5), pizza);
			String prefix = "";
			if(maara > 1){ prefix += maara + "x "; }
				request.getSession().setAttribute("notification", prefix + pizza.getNimi() + " lisättiin koriin!" );
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
		System.out.println("Mausteita löydettiin " + mausteet.size());
		return mausteet;
	}
	
	public ArrayList<Tayte> haeTaytteet(String[] valitut_taytteet){
		ArrayList<Tayte> taytteet = new ArrayList<>();
		if(valitut_taytteet != null){
			try{
				ArrayList<Tayte> kaikki_taytteet = new TayteDAO().haeTaytteet();
				for(Tayte x : kaikki_taytteet){
					for(String y : valitut_taytteet){
						if(x.getTayte_id().equals(y)){
							taytteet.add(x);
						}
					}
				}
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Täytteitä löydettiin " + taytteet.size());
		return taytteet;
	}
	
	
	
}
