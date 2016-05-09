package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.MausteDAO;


@WebServlet("/PoistaMauste")
public class PoistaMausteSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		MausteDAO maustedao = new MausteDAO();
		try{
			if(maustedao.poistaMauste(id)){
				request.getSession().setAttribute("notification", "Mauste " + id + " poistettiin!");
			}else{
				request.getSession().setAttribute("error", "Mauste " + id + " ei voitu poistaa.");
			}
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
		response.sendRedirect("MausteidenHallinta");
		
	}


}
