package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Pizza;
import Pizzacato.model.Utils;
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
       
	/*
	 * 
	 * 	1. Hae parametrit
	 * 	2. Validoi parametrit
	 * 	3. Tsekkaa tehdäänkö tilaus rekisteröidylle asiakkaalle vai ei
	 * 	4. tallenna tilaus.
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			@SuppressWarnings("unchecked")
			Map<String, Pizza> pizzat = (HashMap<String, Pizza>) request.getSession().getAttribute("ostoskori");
			
			// SUORITA TILAUS
			TilausDAO tilausdao = new TilausDAO();
			
			boolean toimitus = false;
			if(request.getParameter("toimitus") != null){
				toimitus = true;
			}
			
			Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");
			if(kayttaja == null){
				String osoite = request.getParameter("osoite");
				String puh = request.getParameter("puh");
				String sahkoposti = request.getParameter("sahkoposti");
				
				kayttaja = new Kayttaja();
				kayttaja.setKayttaja_id(null);
				kayttaja.setKayttajatunnus("asiakas"+new Utils().generate(5));
				kayttaja.setOsoite(osoite);
				kayttaja.setPuh(puh);
				kayttaja.setSahkoposti(sahkoposti);
				kayttaja.setKuljettaja(false);
				kayttaja.setKokki(false);
				kayttaja.setSalasana("");
				kayttaja.setOmistaja(false);
				
			}

			try{
				if(tilausdao.asetaTilaus(pizzat, kayttaja, toimitus)){
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
