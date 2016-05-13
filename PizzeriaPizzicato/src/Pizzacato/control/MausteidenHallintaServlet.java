package Pizzacato.control;

import Pizzacato.model.Mauste;
import Pizzacato.model.dao.MausteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MausteidenHallinta")
public class MausteidenHallintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 	1. Hae mausteet
	 * 	2. Ohjaa mausteiden hallinta sivulle.
	 * 
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Mausteet.jsp");
		
		ArrayList<Mauste> mausteet = new ArrayList<>();
		
		try{
			mausteet = new MausteDAO().haeMausteet();
			Collections.sort(mausteet);
			request.setAttribute("mausteet", mausteet);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
		
		dp.forward(request, response);
	}

}
