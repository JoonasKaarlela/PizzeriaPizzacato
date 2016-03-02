package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class PizzaDAO extends DataAccessObject{

	
	public ArrayList<Pizza> haePizzat() throws SQLException{
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LISTA PIZZOILLE
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM pizza";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		while(results.next()){
			String id = results.getString(1);
			String nimi = results.getString(2);
			String tayte_id = results.getString(3);
			String kuvaus = results.getString(4);
			boolean listalla = results.getBoolean(5);
			double hinta = results.getDouble(6);
			Pizza pizza = new Pizza(id, nimi, tayte_id, kuvaus, listalla, hinta);
			pizzat.add(pizza);
		}
		
		// PALAUTA PIZZAT
		return pizzat;
	}
	

	public void lisaaPizza(Pizza pizza) throws SQLException{
		
	}
	
	public void poistaPizza() throws SQLException{
		
	}
	
	public void piilotaListalla() throws SQLException{
	
	}
	
	public void naytaListalla() throws SQLException{
		
	}
	
	
}
