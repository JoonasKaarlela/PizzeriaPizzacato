package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.Kayttaja;
import Pizzacato.model.dao.KayttajaDAO;



@WebServlet("/Rekisterointi")
public class RekisteroidyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rp = request.getServletContext().getRequestDispatcher("/view/rekisterointi.jsp");
		rp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String kayttajatunnus = request.getParameter("kayttajatunnus_rek");
		String salasana = request.getParameter("salasana_rek");
		String osoite = request.getParameter("osoite_rek");
		String puh = request.getParameter("puh_rek");
		String sahkoposti = request.getParameter("sahkoposti_rek");
		
		Kayttaja kayttaja = rekisteroidy(request, kayttajatunnus, salasana, osoite, sahkoposti, puh);
		if(kayttaja != null){
			request.getSession().setAttribute("notification", "Tervetuloa " + kayttaja.getKayttajatunnus() + "!");
		}else{
			request.getSession().setAttribute("notification", "Rekisteröityminen epäonnisui!");
		}
		
		response.sendRedirect("Menu");
		
	}
	
	public Kayttaja rekisteroidy(HttpServletRequest request, String kayttajatunnus, String salasana, String osoite, String sahkoposti, String puh){
		KayttajaDAO kayttajadao = new KayttajaDAO();
		Kayttaja kayttaja = null;
		try{
			kayttaja = (Kayttaja) kayttajadao.rekisteroidy(kayttajatunnus, salasana, osoite, sahkoposti, puh);
			if(kayttaja != null){
				request.getSession(true).setAttribute("kayttaja", kayttaja);
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return kayttaja;
	}

}
