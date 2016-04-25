package Pizzacato.control;

import Pizzacato.model.Mauste;
import Pizzacato.model.dao.MausteDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MausteidenHallinta")
public class MausteidenHallintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Mausteet.jsp");
		
		ArrayList<Mauste> mausteet = new ArrayList<>();
		
		try{
			mausteet = new MausteDAO().haeMausteet();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("mausteet", mausteet);
		
		dp.forward(request, response);
	}

}
