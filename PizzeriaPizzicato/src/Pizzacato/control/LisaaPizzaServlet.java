package Pizzacato.control;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;












import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Pizzacato.model.Pizza;
import Pizzacato.model.Tayte;
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/lisaa")
public class LisaaPizzaServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = (int) Math.floor( Math.random()*100000 );
		System.out.println(id);
		String pizza_id = "" + id;
		
		String nimi = request.getParameter("nimi");
		ArrayList<Tayte> taytteet = new ArrayList<>();	// request.getParameter("selected");
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = false;
		double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "pizza1.png";

		Pizza pizza = new Pizza(pizza_id, nimi, taytteet, kuvaus, listalla, hinta, kuva);

		PizzaDAO dao = new PizzaDAO();
		try{
			dao.lisaaPizza(pizza);
			response.sendRedirect("Menu");
		} catch(SQLException e){
			System.out.println("ERROR: " + e.getMessage());
			response.sendRedirect("Menu");
		}
	}

}


