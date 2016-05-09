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
import Pizzacato.model.Utils;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;


@WebServlet("/LisaaPizza")
public class LisaaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = new Utils().generate(5);
		String nimi = request.getParameter("nimi");
		ArrayList<Tayte> taytteet = haeTaytteet(request.getParameterValues("taytteet"));
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = listalla(request.getParameter("listalla"));
		double hinta = Double.parseDouble(request.getParameter("hinta").replace(',', '.'));
		String kuva = "pizza1.png";
		
		Pizza pizza = new Pizza(id, nimi, taytteet, kuvaus, listalla, hinta, kuva);
		
		Validate validate = new Validate();
		
		if(validate.teksti(nimi) && validate.teksti(kuvaus) && validate.hinta(request.getParameter("hinta"))){
			if(lisaaPizza(pizza)){
				request.getSession(false).setAttribute("notification", pizza.getNimi() + " lisättiin!");
			}else{
				request.getSession(false).setAttribute("notification", "Pizzaa on jo listalla.");
			}
		}else{
			request.getSession(false).setAttribute("error", "Virheelliset kentät");
		}
		
		
		response.sendRedirect("PizzojenHallinta");
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
	
	public boolean listalla(String checked){
		if(checked != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean lisaaPizza(Pizza pizza){
		PizzaDAO dao = new PizzaDAO();
		try{
			return dao.lisaaPizza(pizza);
		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}


