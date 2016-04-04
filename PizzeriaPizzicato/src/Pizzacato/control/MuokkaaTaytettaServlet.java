package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Tayte;
import Pizzacato.model.dao.TayteDAO;

/**
 * Servlet implementation class MuokkaaTayteServlet
 */
@WebServlet("/MuokkaaTaytetta")
public class MuokkaaTaytettaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tayte_id = request.getParameter("tayte_id");
		String nimi = request.getParameter("nimi");
		String alkupera = request.getParameter("alkupera");
		String kuvaus = request.getParameter("kuvaus");
		Double hinta = Double.parseDouble(request.getParameter("hinta"));
		
		Tayte tayte = new Tayte(tayte_id, nimi, alkupera, kuvaus, hinta);
		muokkaaTaytetta(tayte);
		
		response.sendRedirect("taytteet");
		
	}
	
	public void muokkaaTaytetta(Tayte tayte){
		TayteDAO taytedao = new TayteDAO();
		try{
			taytedao.muokkaaTaytetta(tayte);
		} catch (SQLException e){
			System.out.println("ERROR: " + e.getMessage());
		}
	}
	
	

}
