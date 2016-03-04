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

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;

/**
 * Matias K
 */
@WebServlet("/Menu")
public class ListaaPizzatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// Luodaan pizzadao ja haetaan kaikki pizzat
		PizzaDAO pizzadao = new PizzaDAO();
		ArrayList<Pizza> pizzat = new ArrayList<>();
		try {
			pizzat = pizzadao.haePizzat();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		// Tallennetaan request-olion alle kaikki pizzat
		request.setAttribute("pizzat", pizzat);
		
		String jsp = "/view/Menu.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
