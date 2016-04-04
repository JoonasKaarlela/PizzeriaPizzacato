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
import javax.servlet.http.HttpSession;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;

@WebServlet("/Menu")
public class ListaaPizzatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sivu = "/view/Menu.jsp";
		
		hoidaErrorit(request);
		
		Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");
		
		if( kayttaja != null){
			if(kirjautunut(request.getSession()) && kayttaja.isOmistaja()){
				sivu = "/view/Omistaja.jsp";
			}
		}
		
		ArrayList<Pizza> pizzat = haePizzat();
		request.setAttribute("pizzat", pizzat);
		
		RequestDispatcher dp  = getServletContext().getRequestDispatcher(sivu);
		dp.forward(request, response);
			
	}
	
	
	
	public ArrayList<Pizza> haePizzat(){
		ArrayList<Pizza> pizzat = new ArrayList<>();
		PizzaDAO pizzadao = new PizzaDAO();
				try {
					pizzat = pizzadao.haePizzat();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		return pizzat;
	}

	
	
	public boolean kirjautunut(HttpSession session){
		try{
			Kayttaja kayttaja = (Kayttaja) session.getAttribute("kayttaja");
			return true;
		} catch(NullPointerException e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	public void hoidaErrorit(HttpServletRequest request){
		request.removeAttribute("error");
		request.setAttribute("error", (String) request.getSession().getAttribute("error"));
	}
	

}
