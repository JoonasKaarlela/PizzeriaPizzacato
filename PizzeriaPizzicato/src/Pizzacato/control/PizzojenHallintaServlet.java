package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;
import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;


@WebServlet("/PizzojenHallinta")
public class PizzojenHallintaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sivu = "/view/Pizzat.jsp";
		
		ArrayList<Pizza> pizzat = haePizzat();
		request.setAttribute("pizzat", pizzat);
		
		Collections.sort(pizzat);
		
		ArrayList<Tayte> taytteet = haeTaytteet();
		request.setAttribute("taytteet", taytteet);
		
		
		RequestDispatcher dp = getServletContext().getRequestDispatcher(sivu);
		dp.forward(request, response);
		
		
	}
	
	public ArrayList<Pizza> haePizzat() {
		ArrayList<Pizza> pizzat = new ArrayList<>();
		PizzaDAO pizzadao = new PizzaDAO();
			try {
				pizzat = pizzadao.haePizzat();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return pizzat;
	}

	public ArrayList<Tayte> haeTaytteet(){
		ArrayList<Tayte> taytteet = new ArrayList<>();
		TayteDAO taytedao = new TayteDAO();
		try{
			taytteet = taytedao.haeTaytteet();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return taytteet;
	}

}
