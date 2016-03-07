package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.dao.KayttajaDAO;


@WebServlet("/Kirjaudu")
public class KirjauduServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HAE JSP SIVULTA KAYTTAJA TIEDOT
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		String salasana = request.getParameter("salasana");
		
		KayttajaDAO kayttajadao = new KayttajaDAO();
		
		// LUO TYHJÄ KÄYTTÄJÄ
		Kayttaja kayttaja = null;
		
		// KOKEILE KIRJAUTUA SISÄÄN.
		try{
			kayttaja = (Kayttaja) kayttajadao.haeKayttaja(kayttajatunnus, salasana);
		} catch(SQLException e){
			System.out.println("Kirjautuminen epäonnistui");
			System.out.println(e.getMessage());
		}
		
		// TSEKATAAN LÖYTYIKÖ KÄYTTÄJÄ
		// JOS OK => TALLENNA TALLENNA UUTEEN "SESSIOON"
		if( kayttaja != null){
			request.getSession(true).setAttribute("kayttaja", kayttaja);
			request.getSession(false).setAttribute("token", "123");
		}
		// OHJAA MENU SIVULLE
		response.sendRedirect("Menu");
		
	}

}
