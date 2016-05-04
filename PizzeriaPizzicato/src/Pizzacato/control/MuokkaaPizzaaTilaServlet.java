package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.PizzaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MuokkaaPizzaaTila")
public class MuokkaaPizzaaTilaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sivu = "/views/Pizzat.jsp";
		
		String id = request.getParameter("id");
		
		Pizza pizza = null;
		
		PizzaDAO pizzadao = new PizzaDAO();
		try{
			pizza = pizzadao.haePizza(id);
			request.setAttribute("pizza", pizza);
			sivu = "/views/Pizza.jsp";		
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.getSession(false).setAttribute("notification", "Pizzaa ei voitu hakea");
		}
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(sivu);
		rd.forward(request, response);
		
	}

}
