package Pizzacato.model.dao;

import java.sql.Connection;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class TilausDAO extends DataAccessObject {
	
	public void asetaTilaus(ArrayList<Pizza> pizzat){
		Connection conn = getConnection();
		// LUO TILAUKSENPIZZAT
	}
	
	public void haeTilauksenPizzat(String id){
		Connection conn = getConnection();
		// HAE TIETYN TILAUKSEN PIZZAT
	}
	
}
