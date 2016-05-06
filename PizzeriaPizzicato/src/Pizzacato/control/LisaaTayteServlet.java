package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Tayte;
import Pizzacato.model.Utils;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/LisaaTayte")
public class LisaaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tayte_id = new Utils().generate(5);
		String nimi = request.getParameter ("nimi");
		String alkupera = request.getParameter("alkupera");
		String kuvaus = request.getParameter("kuvaus");
		double hinta = Double.parseDouble(request.getParameter("hinta").replace(',','.'));
		 
		Validate validate = new Validate();
		
		if(validate.teksti(nimi) && validate.teksti(alkupera) && validate.teksti(kuvaus) && validate.hinta(request.getParameter("hinta"))){
			Tayte tayte = new Tayte(tayte_id, nimi, alkupera, kuvaus, hinta);
			if(lisaaTayte(tayte)){
				request.getSession(false).setAttribute("notification", tayte.getNimi() + " lisättiin!");
			}
		}else{
			request.getSession(false).setAttribute("error", "Virheelliset kentät");
		}
		
		response.sendRedirect("TaytteidenHallinta");
	}
		
	public boolean lisaaTayte(Tayte tayte){
		TayteDAO taytedao = new TayteDAO();
		try{
			taytedao.lisaaTayte(tayte);
			return true;
		} catch(SQLException e){
			System.out.println(e.getMessage());
			
		}
		return false;
	}
	

}
