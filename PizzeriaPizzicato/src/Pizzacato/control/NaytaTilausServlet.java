package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Kayttaja;

import java.sql.SQLException;
import java.util.ArrayList;

import Pizzacato.model.Tilaus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TilausDAO;

@WebServlet("/NaytaTilaus")
public class NaytaTilausServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			TilausDAO tilausdao = new TilausDAO();
			
			
			Kayttaja kayttaja = (Kayttaja) request.getSession().getAttribute("kayttaja");
			
			
			try{
				ArrayList<Tilaus> tilaukset = tilausdao.haeTilaukset(kayttaja);
				request.setAttribute("tilaukset", tilaukset);
			}catch(SQLException error){
				System.out.println(error.getMessage());
			}
		
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/Tilaukset.jsp");
			rd.forward(request, response);

	}
} 
