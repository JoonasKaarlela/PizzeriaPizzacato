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
import javax.servlet.http.HttpSession;

import Pizzacato.model.dao.TayteDAO;
import Pizzacato.model.Kayttaja;
import Pizzacato.model.Tayte;


@WebServlet("/Taytteet")
public class ListaaTaytteetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sivu = "/view/Taytteet.jsp";
		
		hoidaErrorit(request);
		
		Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");
		
		if( kayttaja != null){
			if( onKirjautunut(request.getSession()) && kayttaja.isOmistaja() ){
				sivu = "/view/Taytteet.jsp";
			
			} 
		} else {
			sivu = "/Menu";
		}
		
		try{
			ArrayList<Tayte> taytteet = haeTaytteet();
			request.setAttribute("taytteet", taytteet);
		
			RequestDispatcher dp  = getServletContext().getRequestDispatcher(sivu);
			dp.forward(request, response);
			
		} catch(NullPointerException e){
			
			ArrayList<Tayte> taytteet = haeTaytteet();
			request.setAttribute("taytteet", taytteet);
		
			RequestDispatcher dp  = getServletContext().getRequestDispatcher(sivu);
			dp.forward(request, response);
			
		}

		
	}

	
	public ArrayList<Tayte> haeTaytteet() {
		ArrayList<Tayte> taytteet = new ArrayList<>();
		TayteDAO taytedao = new TayteDAO();
			try {
				taytteet = taytedao.haeTaytteet();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return taytteet;
	}
	
	public boolean onKirjautunut(HttpSession session){
		try{
			@SuppressWarnings("unused")
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
