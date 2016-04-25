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

import Pizzacato.model.Kayttaja;
import Pizzacato.model.Tilaus;
import Pizzacato.model.dao.TilausDAO;


@WebServlet("/AdminTilausServlet")
public class HallintasivuTilauksetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sivu = "/view/Admintilaukset.jsp";
		
		ArrayList<Tilaus> tilaukset = haeTilaukset();
		request.setAttribute("tilaukset", tilaukset);
		
			
		RequestDispatcher dp  = getServletContext().getRequestDispatcher(sivu);
		dp.forward(request, response);
		
	}

		public ArrayList<Tilaus> haeTilaukset() {
			ArrayList<Tilaus> tilaukset = new ArrayList<>();
			TilausDAO tilausdao = new TilausDAO ();
			try {
				tilaukset = tilausdao.haeTilaukset(null);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return tilaukset;

		
	}

}
