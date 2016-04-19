package Pizzacato.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.owasp.encoder.Encode;

import com.sun.xml.internal.bind.v2.runtime.output.Encoded;


@WebServlet("/RekisteroidyServlet")
public class RekisteroidyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rp = request.getServletContext().getRequestDispatcher("/view/rekisteroidy.jsp");
		rp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Kerää data
		// Rekisteröidy KayttajaDAO:n rekisteroidy metodilla...
		
	}

}
