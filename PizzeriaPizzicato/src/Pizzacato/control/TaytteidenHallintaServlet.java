package Pizzacato.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Pizzacato.model.Tayte;
import Pizzacato.model.dao.TayteDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TaytteidenHallinta")
public class TaytteidenHallintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Taytteet.jsp");
		
		ArrayList<Tayte> taytteet = haeTaytteet();
		request.setAttribute("taytteet", taytteet);
		
		dp.forward(request, response);
	}

	public ArrayList<Tayte> haeTaytteet() {
		ArrayList<Tayte> taytteet = new ArrayList<>();
		try{
			taytteet = new TayteDAO().haeTaytteet();
			Collections.sort(taytteet);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return taytteet;
	}
}
