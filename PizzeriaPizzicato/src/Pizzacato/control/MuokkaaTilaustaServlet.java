package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TilausDAO;


@WebServlet("/MuokkaaTilausta")
public class MuokkaaTilaustaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String tila = request.getParameter("tila");
		
		TilausDAO tilausdao = new TilausDAO();
		try{
			tilausdao.muokkaaTilausta(id, tila);
			request.getSession().setAttribute("notification", "Tilaus " + id + " tallennettiin!");
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.getSession().setAttribute("error", "Tilausta ei voitu muokata");
		}
		
		response.sendRedirect("TilaustenHallinta");
		
	}

}
