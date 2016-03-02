package Pizzacato.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Pizzacato.control.Arraylist;
import Pizzacato.control.Pizza;
import Pizzacato.control.PizzaDAO;

/**
 * Matias K
 */
@WebServlet("/Menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		// Attribuutit korjataan perjantaina oikean nimisiksi
		
		PizzaDAO pizzadao = new PizzaDAO();
		Arraylist<Pizza> pizzat = pizzadao.findAll();
		
		request.setAttribute("pizzat", pizzat);
		
		String jsp = "/view/Menu.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
