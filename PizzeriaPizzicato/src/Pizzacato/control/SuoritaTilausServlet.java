package Pizzacato.control;

import java.io.IOException;

import Pizzacato.model.Pizza;
import Pizzacato.model.dao.TilausDAO;

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
			ArrayList<Pizza> ostoskori = (ArrayList<Pizza>) request.getSession().getAttribute("ostoskori");
			
			// SUORITA TILAUS
			TilausDAO tilausdao = new TilausDAO();
			tilausdao.asetaTilaus(ostoskori);
		
	}
}
