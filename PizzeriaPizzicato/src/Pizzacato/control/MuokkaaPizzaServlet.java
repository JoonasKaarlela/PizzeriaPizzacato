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
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.PizzanTayteDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/MuokkaaPizzaa")
public class MuokkaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String nimi = request.getParameter("nimi");
		ArrayList<Tayte> taytteet = haeTaytteet(request.getParameterValues("taytteet"));
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = listalla(request.getParameter("piilossa"));
		Double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "pizza1.png";
		
		Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
		if(muokkaaPizzaa(pizza)){
			request.getSession().setAttribute("notification", pizza.getNimi() + " tallennettu!");
		}
		
		response.sendRedirect("Menu");
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
		return pizzan_taytteet;
	}
	
	
	public boolean listalla(String listalla){
		return listalla != null ? true : false;
	}
	
	
	public boolean muokkaaPizzaa(Pizza pizza){
		PizzaDAO pizzadao = new PizzaDAO();
		try{
			pizzadao.muokkaaPizzaa(pizza);
			return true;
		} catch(SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return false;
	}
	
	
}
