package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Mauste;
import Pizzacato.model.Utils;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.MausteDAO;

@WebServlet("/LisaaMauste")
public class LisaaMausteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mauste_id = new Utils().generate(5);
		String nimi = request.getParameter ("nimi");
		double hinta = Double.parseDouble(request.getParameter("hinta").replace(',','.'));
		 
		Validate validate = new Validate();
		Mauste mauste = new Mauste(mauste_id, nimi, hinta);
		
		if(validate.teksti(nimi) && validate.hinta(request.getParameter("hinta"))){
			if(lisaaMauste(mauste)){
				request.getSession().setAttribute("notification", mauste.getNimi() + " lisättiin!");
			}else{
				request.getSession().setAttribute("error", mauste.getNimi() + " on jo listalla.");
			}
		}else{
			request.getSession().setAttribute("error", "Virheellinen muoto");
		}
		
		response.sendRedirect("MausteidenHallinta");
	}
		
	public boolean lisaaMauste(Mauste mauste){
		MausteDAO maustedao = new MausteDAO();
		try{
			return maustedao.lisaaMauste(mauste);
		} catch(SQLException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
}
