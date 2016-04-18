package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/poista")
public class PoistaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		System.out.println(id);
		
		if(poistaPizza(id)){
			request.getSession(false).setAttribute("notification", "pizza " + id + " poistettiin!");
		}
		
		response.sendRedirect("Menu");
	}
	
	public boolean poistaPizza(String id){
		PizzaDAO pizzadao = new PizzaDAO();
		try {
			pizzadao.poistaPizza(id);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
