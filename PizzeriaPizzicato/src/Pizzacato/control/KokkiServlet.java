package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tilaus;
import Pizzacato.model.dao.TilauksenPizzaDAO;
import Pizzacato.model.dao.TilausDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/KokkiServlet")
public class KokkiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/view/Kokki.jsp");
		
		Map<Tilaus, ArrayList<Pizza>> Tilaus = new HashMap<>();
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>(); 
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		
		try{
			tilaukset = new TilausDAO().haeTilaukset();
			
			// Lis‰‰ mappiin tilaukset joiden tila on "vastaanotettu"
			for(Tilaus tilaus : tilaukset){
				if(tilaus.getTila().equals("vastaanotettu")){
					pizzat = new TilauksenPizzaDAO().HaeTilauksenPizzat(tilaus.getTilaus_id());
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
