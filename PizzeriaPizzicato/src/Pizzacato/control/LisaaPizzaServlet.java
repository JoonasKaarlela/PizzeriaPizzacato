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
import Pizzacato.model.dao.PizzaDAO;


@WebServlet("/lisaa")
public class LisaaPizzaServlet extends HttpServlet {
	

			

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pizza_id = String.format("%.1f", Math.floor(Math.random()*Math.random()));		
		String nimi = request.getParameter("nimi");
		String tayte_id = request.getParameter("tayte_id");
		String kuvaus = request.getParameter("kuvaus");
		boolean listalla = false;
		double hinta = Double.parseDouble(request.getParameter("hinta"));
		String kuva = "";
		
		 // Uploadaa kuva
		ServletFileUpload upload = new ServletFileUpload();	
		String folder = getServletContext().getRealPath("") + File.separator + "WebContent";
		
		try{
			List<FileItem> fileItems = (List<FileItem>) upload.parseRequest(request);
			for(FileItem item : fileItems){
				if( !item.isFormField() ){
					String filename = new File(item.getName()).getName();
					String path = folder + File.separator + filename;
					File uploadedFile = new File(path);
					System.out.println( path );
					item.write(uploadedFile);
					kuva = path;
					
					Pizza pizza = new Pizza(pizza_id, nimi, tayte_id, kuvaus, listalla, hinta, kuva);

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
		} catch(Exception e){
			System.out.println("Lisäys ei onnistunut");
			System.out.println(e.getMessage());
			response.sendRedirect("Menu");
		}
		
		
	}

}


