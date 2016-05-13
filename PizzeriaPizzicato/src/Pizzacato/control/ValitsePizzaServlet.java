package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/ValitsePizza")
public class ValitsePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/*
	 * 	1. Hae pizza id:n perusteella
	 * 	2. Ohjaa asiakas Valitse.jsp sivulle. Jossa asiakas voi p‰‰tt‰‰ pizzojen m‰‰r‰n ja mausteet ja lis‰t‰ koriin.
	 * 	
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/view/Valitse.jsp");
		
		String id = request.getParameter("id");
		
		try{
			Pizza pizza = new PizzaDAO().haePizza(id);
			request.setAttribute("pizza", pizza);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		rd.forward(request, response);
	}


}
