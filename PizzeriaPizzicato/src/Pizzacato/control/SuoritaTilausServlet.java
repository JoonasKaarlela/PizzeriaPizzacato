package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Pizza;
import Pizzacato.model.dao.TilausDAO;

import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/suoritaTilaus")
public class SuoritaTilausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			@SuppressWarnings("unchecked")
			HashMap<String, ArrayList<Pizza>> pizzat = (HashMap<String, ArrayList<Pizza>>) request.getSession().getAttribute("ostoskori");
			
			// SUORITA TILAUS
			TilausDAO tilausdao = new TilausDAO();
			
			boolean toimitus = false;
			if(request.getParameter("toimitus") != null){
				toimitus = true;
			}
			
			

			try{
				if(tilausdao.asetaTilaus(pizzat, (Kayttaja) request.getSession().getAttribute("kayttaja"), toimitus)){
					request.getSession().setAttribute("notification", "Kiitos tilauksestasi! :)");
				}else{
					request.getSession().setAttribute("error", "Tilausta ei voitu tehdä.");
				}
				
			}catch(SQLException e){
				System.out.println(e.getMessage());
			}
			
			// TYHJENNÄ KORI
			request.getSession(false).removeAttribute("ostoskori");
			
			// OHJAA MENUUN
			response.sendRedirect("Menu");
			
	}
}
