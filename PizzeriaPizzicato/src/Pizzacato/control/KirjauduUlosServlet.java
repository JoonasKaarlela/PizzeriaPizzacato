package Pizzacato.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kirjauduUlos")
public class KirjauduUlosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/* Kirjaudu ulos
	 * 
	 *	1. Tuhoa sessio.
	 *	2. Ilmoita asiakkaalle onnistuneesta ulos kirjautumisesta.
	 *	3. Ohjaa asiakas Menu:un.
	 * 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getSession(true).setAttribute("notification", "Tervetuloa uudelleen!");
		response.sendRedirect("Menu");
	}
}
