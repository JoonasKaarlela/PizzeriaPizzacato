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
import Pizzacato.model.Utils;
import Pizzacato.model.Validate;
import Pizzacato.model.dao.KayttajaDAO;


@WebServlet("/Kirjaudu")
public class KirjauduServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* Kirjautuminen
	 * 
	 * 1. Hae parametrein� annetut tunnukset.
	 * 2. Validoi sy�tetyt parametrit Validate luokan metodeilla.
	 * 3. Jos kaikki ok, tyhjenn� ostoskori jotta mahdollisen edellisen asiakkaan valitsemat tuotteet nollaantuu.
	 * 4. Jos kirjautuminen onnistui annetuilla parametreill�, tallenna DAO:n palauttama Kayttaja luokan olio requestin HTTPSession olioon.
	 * 5. Ilmoita asiakkaalle onnistuneesta kirjautumisesta.
	 * 6. Ilmoita my�s mik�li virheit� tapahtui operaation yhteydess�.
	 * 
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		String salasana = request.getParameter("salasana");
		
		Validate validate = new Validate();
		
		if(validate.teksti(kayttajatunnus) && validate.salasana(salasana)){
			if(kirjaudu(request, kayttajatunnus, salasana)){
				request.getSession().removeAttribute("ostoskori");
				request.getSession().setAttribute("notification", "Tervetuloa!");
			}else{
				request.getSession().setAttribute("error", "Kirjautuminen ep�onnistui!");
			}
		}else{
			request.getSession().setAttribute("error", "Virheelliset tiedot");
		}
		
		response.sendRedirect("Menu");
	}

	public boolean kirjaudu(HttpServletRequest request, String kayttajatunnus, String salasana){
		KayttajaDAO kayttajadao = new KayttajaDAO();
		try{
			Kayttaja kayttaja = (Kayttaja) kayttajadao.haeKayttaja(kayttajatunnus, salasana);
			if( kayttaja != null){
				request.getSession(true).setAttribute("kayttaja", kayttaja);
				return true;
			} else {
				request.getSession().setAttribute("error", "Kirjautuminen ep�onnistui!");
				return false;
			}
		} catch(SQLException e){
			request.getSession().setAttribute("error", "Kirjautuminen ep�onnistui!");
			System.out.println(e.getMessage());
			return false;
		}
	}
	
}
