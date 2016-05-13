package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TilausDAO;

@WebServlet("/PoistaTilaus")
public class PoistaTilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * 	1. Poista parametrinä annetun id:n omaava tilaus
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("tilaus_id");

		TilausDAO tilausdao = new TilausDAO();
		try{
			if(tilausdao.poistaTilaus(id)){
				request.getSession().setAttribute("notification", "Tilaus " + id + " on peruutettu!");
			}else{
				request.getSession().setAttribute("error", "Tilausta ei voitu poistaa");
			}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.getSession().setAttribute("error", "Tilausta ei voitu poistaa");
		}
		
		response.sendRedirect("Tilaukset");
	}

}
