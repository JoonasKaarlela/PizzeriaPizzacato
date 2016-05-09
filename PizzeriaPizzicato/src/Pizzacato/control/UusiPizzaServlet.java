package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Tayte;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/UusiPizza")
public class UusiPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/views/LuoPizza.jsp");
		
		ArrayList<Tayte> taytteet = new ArrayList<>();
		
		try{
			taytteet = new TayteDAO().haeTaytteet();
			request.setAttribute("taytteet", taytteet);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		
		rd.forward(request, response);
	}


}
