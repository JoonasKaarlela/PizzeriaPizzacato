package Pizzacato.control;

import Pizzacato.model.Tilaus;
import Pizzacato.model.dao.TilausDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Kuljettaja")
public class KuljettajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/*
	 * 
	 * 	1. 	Hae kuljettajalle oleelliset tilaukset ( tilassa "odottaa toimitusta" tai "toimituksessa" )
	 * 	2.	Aseta tilaukset tilaus järjestykseen
	 * 	3.	Ohjaa kuljettaja hallinointi sivulle.
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/view/Kuljettaja.jsp");
		
		ArrayList<Tilaus> tilaukset = new ArrayList<>();
		
		try{
			tilaukset = new TilausDAO().haeTilaukset();
			
			ArrayList<Tilaus> poistettavat = new ArrayList<>();
			for(Tilaus tilaus : tilaukset){
				if(!tilaus.getTila().equals("odottaa toimitusta") && !tilaus.getTila().equals("toimituksessa")){
					poistettavat.add(tilaus);
				}
			}
			tilaukset.removeAll(poistettavat);
			
			Collections.sort(tilaukset);
			request.setAttribute("tilaukset", tilaukset);
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		rd.forward(request, response);
	}


}
