package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Pizzacato.model.Pizza;

public class PizzaDAO extends DataAccessObject{

	
	public ArrayList<Pizza> haePizzat() throws SQLException{
		//HAETAAN KAIKKI PIZZAT
		
		// YHTEYS
		Connection conn = getConnection();
		
		// TYHJÄ LISTA PIZZOILLE
		ArrayList<Pizza> pizzat = new ArrayList<>();
		
		// HAKU LAUSE
		String query = "SELECT * FROM pizza";
		Statement statement = conn.createStatement();
		ResultSet results = statement.executeQuery(query);
		
		// ITEROI TULOKSET LÄPI => LUO UUSI PIZZA OLIO => LISÄÄ LISTAAN...
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
		// LISÄÄ UUSI PIZZA TIETOKANTAAN.
		
		// YHTEYS
		Connection conn = getConnection();
		
		// LISÄYS LAUSE
		String query = "INSERT INTO pizza(pizzaId, nimi, tayteId, kuvaus, listalla, hinta) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, pizza.getPizzaId());
		statement.setString(2, pizza.getNimi());
		statement.setString(3, pizza.getTayteId());
		statement.setString(4, pizza.getKuvaus());
		statement.setBoolean(5, pizza.getListalla());
		statement.setDouble(6, pizza.getHinta());
		
		int syotettiin = statement.executeUpdate();
		if(syotettiin > 1){
			System.out.println("uusi pizza lisättiin!");
		}
		
	}
	
	public void poistaPizza() throws SQLException{
		
	}
	
	public void piilotaListalla() throws SQLException{
	
	}
	
	public void naytaListalla() throws SQLException{
		
	}
	
	
}
