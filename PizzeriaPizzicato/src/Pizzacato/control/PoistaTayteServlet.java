package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TayteDAO;

@WebServlet("/PoistaTayte")
public class PoistaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * 	1. Poista parametrinä annetun id:n omaava tayte
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tayte_id");
		
		if(poistaTayte(id)){
			request.getSession(false).setAttribute("notification", "tayte " + id + " poistettiin!");
		}

		response.sendRedirect("TaytteidenHallinta");
	}

	public boolean poistaTayte(String id) {
		TayteDAO taytedao = new TayteDAO();
		try {
			return taytedao.poistaTayte(id);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
