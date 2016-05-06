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
import Pizzacato.model.Tayte;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.PizzanTayteDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/MuokkaaPizzaa")
public class MuokkaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String nimi = request.getParameter("nimi");
		String kuvaus = request.getParameter("kuvaus");
		ArrayList<Tayte> taytteet = haeTaytteet(request.getParameterValues("tayte"));
		boolean listalla = listalla(request.getParameter("listalla"));
		Double hinta = Double.parseDouble(request.getParameter("hinta").replace(',','.'));
		String kuva = "pizza1.png";
		
		Validate validate = new Validate();
		
		if(validate.nimi(nimi) && validate.nimi(kuvaus) && validate.hinta(request.getParameter("hinta"))){
			Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
			if(muokkaaPizzaa(pizza)){
				request.getSession().setAttribute("notification", pizza.getNimi() + " tallennettu!");
			}else{
				request.getSession().setAttribute("error", pizza.getNimi() + " ei voitu tallentaa");
			}
		}else{
			request.getSession().setAttribute("error", "Virheelliset kentät");
		}
		
		response.sendRedirect("PizzojenHallinta");
	}
	
	
	public ArrayList<Tayte> haeTaytteet(String[] taytteet){
		ArrayList<Tayte> pizzan_taytteet = new ArrayList<>();
		TayteDAO taytedao = new TayteDAO();
		if(taytteet != null){
			try{
				ArrayList<Tayte> kaikki_taytteet = taytedao.haeTaytteet();
				for(Tayte tayte : kaikki_taytteet){
					for(String tayte_id : taytteet){
						if(tayte.getTayte_id().equals(tayte_id)){
							pizzan_taytteet.add(tayte);
						}
					}
				}
			} catch(SQLException e){
				System.out.println(e.getMessage());
			}
		}
		for(Tayte tayte : pizzan_taytteet){
			System.out.println(tayte.getNimi());
		}
		return pizzan_taytteet;
	}
	
	
	public boolean listalla(String listalla){
		if(listalla != null){
			System.out.println("ok");
			return true;
		} else {
			System.out.println("null");
			return false;
		}
	}
	
	
	public boolean muokkaaPizzaa(Pizza pizza){
		PizzaDAO pizzadao = new PizzaDAO();
		try{
			pizzadao.muokkaaPizzaa(pizza);
			return true;
		} catch(SQLException e){
			System.out.println("EI VOITU MUOKATA PIZZAA");
			System.out.println("ERROR: " + e.getMessage());
		}
		return false;
	}
	
	
}
