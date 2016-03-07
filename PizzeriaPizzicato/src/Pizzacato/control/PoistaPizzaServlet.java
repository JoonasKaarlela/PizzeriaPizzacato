package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/PoistaPizzaServlet")
public class PoistaPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String poistettava_id = request.getParameter("id");
		
		PizzaDAO pizzadao = new PizzaDAO();
		try {
			pizzadao.poistaPizza(poistettava_id);
		} catch (SQLException e) {
			System.out.println("Sovelluksessa tapahtui virhe "+ e.getMessage());
			e.printStackTrace();
		}
		
		response.sendRedirect("Menu");
		
	}

}
