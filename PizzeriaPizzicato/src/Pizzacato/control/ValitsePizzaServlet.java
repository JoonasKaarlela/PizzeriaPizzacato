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
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/valitse")
public class ValitsePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getServletContext().getRequestDispatcher("/view/Pizza.jsp");
		
		String id = request.getParameter("pizza_id");
		
		PizzaDAO pizzadao = new PizzaDAO();
		TayteDAO taytedao = new TayteDAO();
		
		Pizza pizza;
		ArrayList<Tayte> taytteet = new ArrayList<>();
		try{
			
			pizza = pizzadao.haePizza(id);
			request.setAttribute("pizza", pizza);
			
			taytteet = taytedao.haeTaytteet();
			request.setAttribute("taytteet", taytteet);
			
			dp.forward(request, response);
			
		}catch(SQLException | NullPointerException e){
			System.out.println(e.getMessage());
			response.sendRedirect("Menu");
		}
	}


}
