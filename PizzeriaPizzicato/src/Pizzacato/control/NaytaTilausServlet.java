package Pizzacato.control;

import java.io.IOException;

import java.sql.SQLException;

import Pizzacato.model.Tilaus;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import Pizzacato.model.dao.TilausDAO;

@WebServlet("/NaytaTilaus")
public class NaytaTilausServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



	}

	public void naytaTilaus(String id) {

		TilausDAO tilausdao = new TilausDAO();

		tilausdao.naytaTilaus(id);

}

} 
