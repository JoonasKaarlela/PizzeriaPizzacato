package Pizzacato.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TayteDAO;

@WebServlet("/PoistaTayte")
public class PoistaTayteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("tayte_id");
		
		if(poistaTayte(id)){
			request.getSession(false).setAttribute("notification", "tayte " + id + " poistettiin!");
		}

		response.sendRedirect("Taytteet");
	}

	public boolean poistaTayte(String id) {
		TayteDAO taytedao = new TayteDAO();
		try {
			taytedao.poistaTayte(id);
			return true;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}
