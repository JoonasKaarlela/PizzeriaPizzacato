package Pizzacato.control;

import Pizzacato.model.Mauste;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.MausteDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MuokkaaMaustetta")
public class MuokkaaMaustettaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nimi = request.getParameter("nimi");
		double hinta = Double.parseDouble(request.getParameter("hinta").replace(',', '.'));
		
		
		Validate validate = new Validate();
		
		if(validate.nimi(nimi) && validate.hinta(request.getParameter("hinta"))){
			Mauste mauste = new Mauste(id, nimi, hinta);
			try{
				new MausteDAO().muokkaaMaustetta(mauste);
				System.out.println("ok");
			}catch(SQLException e){
				request.getSession().setAttribute("notification", "Maustetta ei voitu muokata");
			}
		}else{
			request.getSession().setAttribute("error", "Virheelliset kentät");
		}
		
	
		response.sendRedirect("MausteidenHallinta");
		
	}

}
