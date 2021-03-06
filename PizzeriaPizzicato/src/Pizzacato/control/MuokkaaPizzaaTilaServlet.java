package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;
import Pizzacato.model.dao.TayteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MuokkaaPizzaaTila")
public class MuokkaaPizzaaTilaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/*
	 * 	1. Hae muokattava pizza
	 * 	2. Hae valittavat t�ytteet
	 * 	3. J�rjest� t�ytteet
	 * 	4. Ohjaa admin pizzan muokkaus sivulle
	 * 
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sivu = "/view/Pizzat.jsp";
		String id = request.getParameter("id");
		Pizza pizza = null;	
		ArrayList<Tayte> taytteet = new ArrayList<>();
		PizzaDAO pizzadao = new PizzaDAO();
		TayteDAO taytedao = new TayteDAO();
		
		try{
			pizza = pizzadao.haePizza(id);
			request.setAttribute("pizza", pizza);
			sivu = "/view/Pizza.jsp";		
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.getSession(false).setAttribute("error", "Pizzaa ei voitu hakea");
		}
		
		try{
			taytteet = taytedao.haeTaytteet();
			Collections.sort(taytteet);
			request.setAttribute("taytteet", taytteet);
		}catch(SQLException e){
			System.out.println(e.getMessage());
			request.getSession(false).setAttribute("error", "T�ytteit� ei voitu hakea");
		}
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher(sivu);
		rd.forward(request, response);
		
	}

}
