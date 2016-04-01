package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.PizzaDAO;

@WebServlet("/piilota")
public class PiilotapizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("pizza_id");
		piilotaPizza(id);
		
		response.sendRedirect("Menu");	
	}
	
	public void piilotaPizza(String id){
		try{
			PizzaDAO pizzadao = new PizzaDAO();
			pizzadao.piilotaListalla(id);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
	
