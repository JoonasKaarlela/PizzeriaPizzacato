package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;


@WebServlet("/lisaa")
public class LisaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = generate(5);
		String nimi = request.getParameter("nimi");
		ArrayList<Tayte> taytteet = haeTaytteet(request.getParameterValues("taytteet"));
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = false;
		double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "pizza1.png";
		
		Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
		lisaaPizza(pizza);
		
		response.sendRedirect("Menu");
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
	
	
	
	public void lisaaPizza(Pizza pizza){
		PizzaDAO dao = new PizzaDAO();
		try{
			dao.lisaaPizza(pizza);
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public String generate(int length){
		String code = "";
		for (int i = 0; i < length; i++) {
			code += "" + new Random().nextInt(0 - 9 + 1);
		}
		return code;
	}

}


