package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tilaus;
import Pizzacato.model.dao.TilauksenPizzaDAO;
import Pizzacato.model.dao.TilausDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Kokki")
public class KokkiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * 
	 * 	1. Hae kokille oleelliset tilaukset (tilassa "vastaanotettu" tai "valmimstuksessa")
	 * 	2. Hae tilaus kohtaiset pizza tilauksille, jotta kokki n‰kee mitk‰ pizzat tilaukseen tulee valmistaa.
	 * 	3. Uudelleen j‰rjest‰ pizzat aakkosj‰rjestykseen.
	 * 	4. Tallenna rajapinnan Map toteuttavan hashmap luokan olioon avaimeksi Tilaus ja arvoksi ArrayList<Pizza>.
	 * 	4. Ohjaa kokki hallinnointi sivulle. 
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/view/Kokki.jsp");
		
		Map<Tilaus, ArrayList<Pizza>> Tilaus = new HashMap<>();
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>(); 
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		
		try{
			tilaukset = new TilausDAO().haeTilaukset();
			
			// Lis‰‰ mappiin tilaukset joiden tila on "vastaanotettu" tai "valmistuksessa"
			for(Tilaus tilaus : tilaukset){
				if(tilaus.getTila().equals("vastaanotettu") || tilaus.getTila().equals("valmistuksessa")){
					pizzat = new TilauksenPizzaDAO().HaeTilauksenPizzat(tilaus.getTilaus_id());
					Collections.sort(pizzat);
					Tilaus.put(tilaus, pizzat);
				}
			}
			request.setAttribute("tilaukset", Tilaus);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		

		rd.forward(request, response);
		
	}



}
